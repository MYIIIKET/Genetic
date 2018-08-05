package engine.base;

import engine.interfaces.Computable;
import engine.interfaces.DeepCloneable;
import engine.interfaces.Mutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Gene<Value> implements Comparable<Gene<Value>>,
        Mutable<Gene<?>, Gene<?>>,
        DeepCloneable<Gene<Value>>,
        Computable {
    private final UUID code = UUID.randomUUID();
    private Value value;

    @Override
    public Gene<?> mutate(Gene<?> gene) {
        final boolean willBeMutated = Math.random() < 0.5;
        if (willBeMutated) {
            return gene.copy();
        }
        return this;
    }

    public abstract Number getNumberRepresentation();
}
