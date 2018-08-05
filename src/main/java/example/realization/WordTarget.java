package example.realization;

import engine.base.Target;

import java.util.List;

public class WordTarget extends Target<List<LetterGene>> {
    public WordTarget(List<LetterGene> target) {
        super(target);
    }

    @Override
    public Number computeFitnessValue() {
        return 0;
    }
}
