package example;

import engine.Chromosome;
import lombok.Data;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
public class WordChromosome extends Chromosome<LetterGene, WordTarget> {
    public WordChromosome(List<LetterGene> genes, List<LetterGene> allGenes, WordTarget target) {
        super(genes, allGenes, target);
    }

    @Override
    public Chromosome<LetterGene, WordTarget> mutate(int genesToMutate) {
        final int chromosomeLength = getGenes().size();
        int geneToMutate;
        int randomGene;
        for (int i = 0; i < genesToMutate; i++) {
            geneToMutate = (int) (Math.random() * chromosomeLength);
            randomGene = (int) (Math.random() * getAllGenes().size());
            getGenes().set(geneToMutate, (LetterGene) getGenes().get(genesToMutate).mutate(getAllGenes().get(randomGene)));
        }
        return this;
    }

    @Override
    public boolean isBetterThan(Chromosome<LetterGene, WordTarget> chromosome) {
        return getFitnessValue().byteValue() < chromosome.getFitnessValue().byteValue();
    }

    @Override
    public boolean isCompleted() {
        return getFitnessValue().equals(getTarget().getFitnessValue());
    }

    @Override
    public String toString() {
        return getGenes().stream().map(gene -> String.valueOf(gene.getValue().getValue()))
                .collect(Collectors.joining());
    }

    @Override
    public int compareTo(Chromosome<LetterGene, WordTarget> chromosome) {
        return Math.abs(getFitnessValue().byteValue() - chromosome.getFitnessValue().byteValue());
    }

    @Override
    public Number getFitnessValue() {
        final AtomicInteger index = new AtomicInteger(0);
        final int fitnessValue = getGenes().stream().mapToInt(gene -> Math.abs(gene.getFitnessValue().byteValue()
                - getTarget().getTarget().get(index.getAndIncrement()).getFitnessValue().byteValue())).sum();
        setFitnessValue(fitnessValue);
        return fitnessValue;
    }
}
