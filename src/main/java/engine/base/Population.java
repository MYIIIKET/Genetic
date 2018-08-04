package engine.base;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public abstract class Population<
        GeneImpl extends Gene<?>,
        TargetImpl extends Target<?>,
        ChromosomeImpl extends Chromosome<GeneImpl, TargetImpl>> {
    private UUID id = UUID.randomUUID();
    private List<ChromosomeImpl> chromosomes;
    private int populationSize;
    private long generation;

    public Population(List<ChromosomeImpl> chromosomes) {
        setChromosomes(new ArrayList<>(chromosomes));
        setPopulationSize(chromosomes.size());
    }
}
