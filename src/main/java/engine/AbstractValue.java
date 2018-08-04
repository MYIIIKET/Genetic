package engine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractValue<Value> implements DeepCloneable<AbstractValue<Value>>, Comparable<AbstractValue<Value>> {
    private final UUID code = UUID.randomUUID();
    Value value;

    public abstract Number getNumberRepresentation();
}
