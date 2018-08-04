package example;

import engine.Gene;
import lombok.Data;

@Data
public class LetterGene extends Gene<LetterValue> {
    public LetterGene(LetterValue value) {
        super(value);
    }

    @Override
    public int compareTo(Gene gene) {
        return Math.abs(gene.getValue().getNumberRepresentation().byteValue() - getValue().getNumberRepresentation());
    }

    @Override
    protected Gene<LetterValue> mutate(Gene<LetterValue> gene) {
        final boolean willBeMutated = Math.random() < 0.5;
        if (willBeMutated) {
            return gene.copy();
        }
        return this;
    }

    @Override
    public Number getFitnessValue() {
        return getValue().getNumberRepresentation();
    }
}
