package engine;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public abstract class Chromosome<GeneImpl extends Gene<?>, TargetImpl extends Target<?>>
        implements Comparable<Chromosome<GeneImpl, TargetImpl>>,
        DeepCloneable<Chromosome<GeneImpl, TargetImpl>> {
    private final UUID id = UUID.randomUUID();
    private List<GeneImpl> genes;
    private List<GeneImpl> allGenes;
    private TargetImpl target;
    private Number fitnessValue;

    public Chromosome(List<GeneImpl> genes, List<GeneImpl> allGenes, TargetImpl target) {
        setGenes(new ArrayList<>(genes));
        setTarget(target);
        setAllGenes(allGenes);
        setFitnessValue(getFitnessValue());
    }

    public abstract Number getFitnessValue();

    public abstract Chromosome<GeneImpl, TargetImpl> mutate(int genesToMutate);

    public abstract boolean isBetterThan(Chromosome<GeneImpl, TargetImpl> chromosome);

    public abstract boolean isCompleted();

}
