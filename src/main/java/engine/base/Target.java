package engine.base;

import engine.interfaces.Computable;
import engine.interfaces.DeepCloneable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Target<TargetImpl> implements DeepCloneable<TargetImpl>, Computable {
    TargetImpl target;
}
