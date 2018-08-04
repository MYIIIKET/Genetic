package example;

import engine.AbstractValue;
import lombok.Data;

@Data
public class LetterValue extends AbstractValue<Character> {
    public LetterValue(Character letter) {
        super(letter);
    }

    @Override
    public Byte getNumberRepresentation() {
        return (byte) getValue().charValue();
    }

    @Override
    public int compareTo(AbstractValue<Character> value) {
        return getValue() < value.getValue() ? 1 : -1;
    }
}
