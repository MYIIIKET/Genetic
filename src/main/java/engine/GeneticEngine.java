package engine;

import lombok.Builder;
import lombok.Data;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
@Builder
public class GeneticEngine {
    @Builder.Default
    private int numberOfBest = 4;
    @Builder.Default
    private float crossoverCoverage = 0.7f;
    @Builder.Default
    private float mutateFactor = 0.7f;
    @Builder.Default
    private float mutateFactorDelta = 0.3f;
    @Builder.Default
    private int generationsToUpdateMutateFactor = 10;
    @Builder.Default
    private boolean updateMutateFactor = true;
    private Population population;

    public void evolution() {
        long generation = 0;
        while (true) {
            reproduce(cross(select(getPopulation())));
            Chromosome best = selectBest(getPopulation());

            if (updateMutateFactor && generation % generationsToUpdateMutateFactor == 0) {
                mutateFactor -= mutateFactor * 0.3;
            }

            System.out.println("engine.Chromosome: " + best.toString()
                    + "; Fitness: " + best.getFitnessValue()
                    + "; Generation: " + generation);

            if (best.isCompleted()) {
                break;
            }
            generation++;
        }
        System.out.println("Evolution completed with generation: " + generation);
    }

    private boolean isFitnessChanging() {
        return false;
    }

    private List<Chromosome> select(Population<Chromosome<Gene<AbstractValue<?>>, Target<?>>> population) {
        return population.getChromosomes().stream()
                .sorted(Comparator.comparing(chromosome -> chromosome.getFitnessValue().intValue()))
                .limit(getNumberOfBest()).collect(Collectors.toList());
    }

    private Chromosome selectBest(Population<Chromosome<Gene<AbstractValue<?>>, Target<?>>> population) {
        return population.getChromosomes().stream()
                .min(Comparator.comparing(chromosome -> chromosome.getFitnessValue().intValue()))
                .orElseThrow(() -> new RuntimeException("Not enough elements"));
    }

    private List<Chromosome> cross(List<Chromosome> bestChromosomes) {
        final AtomicInteger counter = new AtomicInteger(0);
        return bestChromosomes
                .stream().collect(Collectors.groupingBy(chromosome -> counter.getAndIncrement() / 2))
                .entrySet()
                .stream().map(this::cross)
                .flatMap(List::stream).collect(Collectors.toList());
    }

    private List<Chromosome> cross(Map.Entry<Integer, List<Chromosome>> entry) {
        final int size = entry.getValue().size();
        if (size > 1) {
            return cross(entry.getValue().get(0), entry.getValue().get(1));
        } else {
            return cross(entry.getValue().get(0), entry.getValue().get(0));
        }
    }

    private List<Chromosome> cross(Chromosome<Gene<AbstractValue<?>>, Target<?>> firstChromosome, Chromosome<Gene<AbstractValue<?>>, Target<?>> secondChromosome) {
        Gene<AbstractValue<?>> tempGene;
        final boolean isFirstPart = Math.random() < 0.5;
        final int size = firstChromosome.getGenes().size();
        if (isFirstPart) {
            for (int i = 0; i < size * getCrossoverCoverage(); i++) {
                tempGene = firstChromosome.getGenes().get(i).copy();
                firstChromosome.getGenes().set(i, secondChromosome.getGenes().get(i).copy());
                secondChromosome.getGenes().set(i, tempGene);
            }
        } else {
            for (int i = (int) (size * getCrossoverCoverage()); i < size; i++) {
                tempGene = firstChromosome.getGenes().get(i).copy();
                firstChromosome.getGenes().set(i, secondChromosome.getGenes().get(i).copy());
                secondChromosome.getGenes().set(i, tempGene);
            }
        }
        return Arrays.asList(firstChromosome, secondChromosome);
    }

    private void reproduce(List<Chromosome<Gene<? extends AbstractValue<?>>, ? extends Target<?>>> chromosomes) {
        final int populationSize = getPopulation().getPopulationSize();
        final int survivorsSize = chromosomes.size();
        final int toProduce = populationSize - survivorsSize;
        int randomSurvivor;
        for (int i = 0; i < toProduce; i++) {
            randomSurvivor = (int) (Math.random() * survivorsSize);
            chromosomes.add(chromosomes.get(randomSurvivor).copy());
        }
        getPopulation().setChromosomes(mutate(chromosomes));
    }

    private List<Chromosome<Gene<? extends AbstractValue<?>>, ? extends Target<?>>> mutate(List<Chromosome<Gene<? extends AbstractValue<?>>, ? extends Target<?>>> chromosomes) {
        final int chromosomeLength = chromosomes.get(0).getGenes().size();
        final int genesToMutate = (int) (chromosomeLength * getMutateFactor());
        for (Chromosome<Gene<? extends AbstractValue<?>>, ? extends Target<?>> chromosome : chromosomes) {
            chromosome.mutate(genesToMutate);
        }
        return chromosomes;
    }
}
