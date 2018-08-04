package example;

import engine.GeneticEngine;
import engine.Population;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' '};
        List<LetterGene> allGenes = initGenes(alphabet);

        char[] targetLetters = {'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd'};
        List<LetterGene> targetGenes = initGenes(targetLetters);

        char[] initialLetters = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        List<LetterGene> initialGenes = initGenes(initialLetters);

        WordTarget wordTarget = new WordTarget(targetGenes);

        int populationSize = 1000;
        List<WordChromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            WordChromosome chromosome = new WordChromosome(initialGenes, allGenes, wordTarget);
            chromosomes.add(chromosome);
        }
        Population wordPopulation = new WordPopulation(chromosomes);

        GeneticEngine engine = GeneticEngine.builder().updateMutateFactor(true).population(wordPopulation).build();

        engine.evolution();
    }

    private static List<LetterGene> initGenes(char[] chars) {
        List<LetterGene> genes = new ArrayList<>();
        for (char letter : chars) {
            LetterGene letterGene = new LetterGene(letter);
            genes.add(letterGene);
        }
        return genes;
    }
}
