package example.realization;

import engine.base.Population;

import java.util.List;

public class WordPopulation extends Population<LetterGene, WordTarget, WordChromosome> {
    public WordPopulation(List<WordChromosome> wordChromosomes) {
        super(wordChromosomes);
    }
}
