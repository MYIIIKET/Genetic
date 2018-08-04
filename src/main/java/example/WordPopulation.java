package example;

import engine.base.Population;

import java.util.List;

public class WordPopulation extends Population<WordChromosome, LetterGene, WordTarget> {
    public WordPopulation(List<WordChromosome> wordChromosomes) {
        super(wordChromosomes);
    }
}
