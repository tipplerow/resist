
package resist.agent;

import java.util.Set;

/**
 * Enumerates the types of cancer cells in a stochastic simulation.
 */
public enum CellType implements AgentType {
    /**
     * The original non-resistant tumor cells.
     */
    NON_RESISTANT(Set.of()),

    /**
     * Cells that are resistant to drug A but not B.
     */
    RESISTANT_A(Set.of(DrugType.A)),

    /**
     * Cells that are resistant to drug B but not A.
     */
    RESISTANT_B(Set.of(DrugType.B)),

    /**
     * Cells that are resistant to both drugs A and B.
     */
    RESISTANT_AB(Set.of(DrugType.A, DrugType.B));

    private final Set<DrugType> resistant;

    private CellType(Set<DrugType> resistant) {
        this.resistant = resistant;
    }

    /**
     * Identifies drugs to which this cell type is resistant.
     *
     * @param drug a drug of interest.
     *
     * @return {@code true} iff this cell type is resistant to the
     * specified drug.
     */
    public boolean isResistant(DrugType drug) {
        return resistant.contains(drug);
    }

    /**
     * Returns a read-only view of the drugs to which this cell type is
     * resistant.
     *
     * @return a read-only view of the drugs to which this cell type is
     * resistant.
     */
    public Set<DrugType> viewResistant() {
        return resistant;
    }

    @Override public boolean isCell() {
        return true;
    }

    @Override public boolean isDrug() {
        return false;
    }
}
