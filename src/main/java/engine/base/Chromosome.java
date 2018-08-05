package engine.base;

import engine.interfaces.Computable;
import engine.interfaces.DeepCloneable;
import engine.interfaces.Mutable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public abstract class Chromosome<GeneImpl extends Gene<?>, TargetImpl extends Target<?>>
        implements Comparable<Chromosome<GeneImpl, TargetImpl>>,
        DeepCloneable<Chromosome<GeneImpl, TargetImpl>>,
        Mutable<Chromosome<GeneImpl, TargetImpl>, Integer>,
        Computable {
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

    @Override
    public Chromosome<GeneImpl, TargetImpl> mutate(Integer genesToMutate) {
        final int chromosomeLength = getGenes().size();
        int geneToMutate;
        int randomGene;
        for (int i = 0; i < genesToMutate; i++) {
            geneToMutate = (int) (Math.random() * chromosomeLength);
            randomGene = (int) (Math.random() * getAllGenes().size());
            getGenes().set(geneToMutate, (GeneImpl) getGenes().get(genesToMutate).mutate(getAllGenes().get(randomGene)));
        }
        return this;
    }

    public abstract boolean isCompleted();

}
