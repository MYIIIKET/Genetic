package engine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Gene<Value> implements Comparable<Gene<Value>>,
        DeepCloneable<Gene<Value>> {
    private final UUID code = UUID.randomUUID();
    private Value value;

    protected abstract Gene<Value> mutate(Gene<Value> gene);

    protected abstract Number getFitnessValue();

    public abstract Number getNumberRepresentation();
}
