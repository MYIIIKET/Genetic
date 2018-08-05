package example.realization;

import engine.base.Chromosome;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
public class WordChromosome extends Chromosome<LetterGene, WordTarget> {
    public WordChromosome(List<LetterGene> genes, List<LetterGene> allGenes, WordTarget target) {
        super(genes, allGenes, target);
    }

    @Override
    public String toString() {
        return getGenes().stream().map(gene -> String.valueOf(gene.getValue()))
                .collect(Collectors.joining());
    }

    @Override
    public int compareTo(Chromosome<LetterGene, WordTarget> chromosome) {
        return Math.abs(getFitnessValue().byteValue() - chromosome.getFitnessValue().byteValue());
    }

    @Override
    public Number computeFitnessValue() {
        final AtomicInteger index = new AtomicInteger(0);
        final int fitnessValue = getGenes().stream().mapToInt(gene -> Math.abs(gene.computeFitnessValue().byteValue()
                - getTarget().getTarget().get(index.getAndIncrement()).computeFitnessValue().byteValue())).sum();
        setFitnessValue(fitnessValue);
        return fitnessValue;
    }
}
