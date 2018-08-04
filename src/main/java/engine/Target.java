package engine;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Target<TargetImpl> implements DeepCloneable<TargetImpl> {
    TargetImpl target;

    public abstract Number getFitnessValue();
}
