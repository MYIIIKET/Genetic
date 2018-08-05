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
        return Math.abs(gene.getNumberRepresentation().intValue() - getNumberRepresentation().intValue());
    }

    @Override
    public Number computeFitnessValue() {
        return getNumberRepresentation();
    }

    @Override
    public Number getNumberRepresentation() {
        return (byte) getValue().charValue();
    }
}
