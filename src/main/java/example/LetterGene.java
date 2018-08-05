package example;

import engine.base.Gene;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LetterGene extends Gene<Character> {
    public LetterGene(Character value) {
        super(value);
    }

    @Override
    public int compareTo(Gene gene) {
        return Math.abs(gene.computeFitnessValue().intValue() - computeFitnessValue().intValue());
    }

    @Override
    public Number computeFitnessValue() {
        return (byte) getValue().charValue();
    }
}
