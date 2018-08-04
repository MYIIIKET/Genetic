package example;

import engine.Population;

import java.util.List;

public class WordPopulation extends Population<WordChromosome> {
    public WordPopulation(List<WordChromosome> wordChromosomes) {
        super(wordChromosomes);
    }
}
