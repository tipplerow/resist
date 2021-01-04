
package resist.system;

import jam.app.JamProperties;
import jam.bravais.Lattice;
import jam.math.DoubleRange;
import jam.math.IntRange;
import jam.space.LatticeSpace;
import jam.space.Space;

/**
 * Assembles all parameters required to define a stochastic
 * simulation.
 */
public final class ResistParam {
    private final Space space;

    private final int siteCapacity;
    private final int initialCellCount;
    private final int initialDrugCount;

    private final double drugDecayRate;
    private final double drugUptakeRate;

    private final double resistantBirthRate;
    private final double resistantDeathRate;

    private final double nonResistantBirthRate;
    private final double nonResistantDeathRate;

    private final double cellMigrationRate;
    private final double drugDiffusionRate;
    private final double resistanceMutationRate;

    private static ResistParam global = null;

    private ResistParam() {
        this.space = resolveSpace();

        this.siteCapacity = resolveSiteCapacity();
        this.initialCellCount = resolveInitialCellCount();
        this.initialDrugCount = resolveInitialDrugCount();

        this.drugDecayRate = resolveDrugDecayRate();
        this.drugUptakeRate = resolveDrugUptakeRate();

        this.resistantBirthRate = resolveResistantBirthRate();
        this.resistantDeathRate = resolveResistantDeathRate();

        this.nonResistantBirthRate = resolveNonResistantBirthRate();
        this.nonResistantDeathRate = resolveNonResistantDeathRate();

        this.cellMigrationRate = resolveCellMigrationRate();
        this.drugDiffusionRate = resolveDrugDiffusionRate();
        this.resistanceMutationRate = resolveResistanceMutationRate();
    }

    /**
     * Name of the system property that specifies the lattice geometry.
     */
    public static final String LATTICE_PROPERTY = "resist.lattice";

    /**
     * Name of the system property that specifies the maximum number
     * of cells at a single lattice site.
     */
    public static final String SITE_CAPACITY_PROPERTY = "resist.siteCapacity";

    /**
     * Name of the system property that specifies the initial number
     * of (non-resistant) cells (across the entire lattice).
     */
    public static final String INITIAL_CELL_COUNT_PROPERTY = "resist.initialCellCount";

    /**
     * Name of the system property that specifies the initial number
     * of therapeutic agents (across the entire lattice).
     */
    public static final String INITIAL_DRUG_COUNT_PROPERTY = "resist.initialDrugCount";

    /**
     * Name of the system property that specifies the rate at which
     * the therapeutic agent decays.
     */
    public static final String DRUG_DECAY_RATE_PROPERTY = "resist.drugDecayRate";

    /**
     * Name of the system property that specifies the rate at which
     * cells consume (take in) the therapeutic agent.
     */
    public static final String DRUG_UPTAKE_RATE_PROPERTY = "resist.drugUptakeRate";

    /**
     * Name of the system property that specifies the birth rate of
     * resistant cells.
     */
    public static final String RESISTANT_BIRTH_RATE_PROPERTY = "resist.resistantBirthRate";

    /**
     * Name of the system property that specifies the natural death
     * rate of resistant cells <em>in the absence of the therapeutic
     * agent</em>.
     */
    public static final String RESISTANT_DEATH_RATE_PROPERTY = "resist.resistantDeathRate";

    /**
     * Name of the system property that specifies the birth rate of
     * non-resistant cells.
     */
    public static final String NON_RESISTANT_BIRTH_RATE_PROPERTY = "resist.nonResistantBirthRate";

    /**
     * Name of the system property that specifies the natural death
     * rate of non-resistant cells <em>in the absence of therapeutic
     * agent</em>.
     */
    public static final String NON_RESISTANT_DEATH_RATE_PROPERTY = "resist.nonResistantDeathRate";

    /**
     * Name of the system property that specifies the rate constant
     * for the migration of cells from site to site.
     */
    public static final String CELL_MIGRATION_RATE_PROPERTY = "resist.cellMigrationRate";

    /**
     * Name of the system property that specifies the rate constant
     * for the diffusion of the therapeutic agent.
     */
    public static final String DRUG_DIFFUSION_RATE_PROPERTY = "resist.drugDiffusionRate";

    /**
     * Name of the system property that specifies the rate at which
     * resistance mutations arise.
     */
    public static final String RESISTANCE_MUTATION_RATE_PROPERTY = "resist.resistanceMutationRate";

    /**
     * Returns the global parameter instance defined by system properties.
     *
     * @return the single parameter instance defined by system properties.
     */
    public static ResistParam global() {
        if (global == null)
            global = new ResistParam();

        return global;
    }

    private static Space resolveSpace() {
        return LatticeSpace.create(resolveLattice());
    }

    private static Lattice resolveLattice() {
        return Lattice.parse(JamProperties.getRequired(LATTICE_PROPERTY));
    }

    private static int resolveSiteCapacity() {
        return JamProperties.getRequiredInt(SITE_CAPACITY_PROPERTY, IntRange.POSITIVE);
    }

    private static int resolveInitialCellCount() {
        return JamProperties.getRequiredInt(INITIAL_CELL_COUNT_PROPERTY, IntRange.POSITIVE);
    }

    private static int resolveInitialDrugCount() {
        return JamProperties.getRequiredInt(INITIAL_DRUG_COUNT_PROPERTY, IntRange.POSITIVE);
    }

    private static double resolveDrugDecayRate() {
        return JamProperties.getRequiredDouble(DRUG_DECAY_RATE_PROPERTY, DoubleRange.NON_NEGATIVE);
    }

    private static double resolveDrugUptakeRate() {
        return JamProperties.getRequiredDouble(DRUG_UPTAKE_RATE_PROPERTY, DoubleRange.NON_NEGATIVE);
    }

    private static double resolveResistantBirthRate() {
        return JamProperties.getRequiredDouble(RESISTANT_BIRTH_RATE_PROPERTY, DoubleRange.POSITIVE);
    }

    private static double resolveResistantDeathRate() {
        return JamProperties.getRequiredDouble(RESISTANT_DEATH_RATE_PROPERTY, DoubleRange.POSITIVE);
    }

    private static double resolveNonResistantBirthRate() {
        return JamProperties.getRequiredDouble(NON_RESISTANT_BIRTH_RATE_PROPERTY, DoubleRange.POSITIVE);
    }

    private static double resolveNonResistantDeathRate() {
        return JamProperties.getRequiredDouble(NON_RESISTANT_DEATH_RATE_PROPERTY, DoubleRange.POSITIVE);
    }

    private static double resolveCellMigrationRate() {
        return JamProperties.getRequiredDouble(CELL_MIGRATION_RATE_PROPERTY, DoubleRange.NON_NEGATIVE);
    }

    private static double resolveDrugDiffusionRate() {
        return JamProperties.getRequiredDouble(DRUG_DIFFUSION_RATE_PROPERTY, DoubleRange.NON_NEGATIVE);
    }

    private static double resolveResistanceMutationRate() {
        return JamProperties.getRequiredDouble(RESISTANCE_MUTATION_RATE_PROPERTY, DoubleRange.POSITIVE);
    }

    /**
     * Returns the spatial structure for the stochastic simulation.
     *
     * @return the spatial structure for the stochastic simulation.
     */
    public Space getSpace() {
        return space;
    }

    /**
     * Returns the maximum number of cells at a single lattice site.
     *
     * @return the maximum number of cells at a single lattice site.
     */
    public int getSiteCapacity() {
        return siteCapacity;
    }

    /**
     * Returns the initial number of (non-resistant) cells across the
     * entire lattice.
     *
     * @return the initial number of (non-resistant) cells across the
     * entire lattice.
     */
    public int getInitialCellCount() {
        return initialCellCount;
    }

    /**
     * Returns the initial number of therapeutic agents across the
     * entire lattice.
     *
     * @return the initial number of therapeutic agents across the
     * entire lattice.
     */
    public int getInitialDrugCount() {
        return initialDrugCount;
    }

    /**
     * Returns the rate at which the therapeutic agent decays.
     *
     * @return the rate at which the therapeutic agent decays.
     */
    public double getDrugDecayRate() {
        return drugDecayRate;
    }

    /**
     * Returns the rate at which cells consume (take in) the
     * therapeutic agent.
     *
     * @return the rate at which cells consume (take in) the
     * therapeutic agent.
     */
    public double getDrugUptakeRate() {
        return drugUptakeRate;
    }

    /**
     * Returns the birth rate of resistant cells.
     *
     * @return the birth rate of resistant cells.
     */
    public double getResistantBirthRate() {
        return resistantBirthRate;
    }

    /**
     * Returns the natural death rate of resistant cells <em>in the
     * absence of the therapeutic agent</em>.
     *
     * @return the natural death rate of resistant cells.
     */
    public double getResistantDeathRate() {
        return resistantDeathRate;
    }

    /**
     * Returns the birth rate of non-resistant cells.
     *
     * @return the birth rate of non-resistant cells.
     */
    public double getNonResistantBirthRate() {
        return nonResistantBirthRate;
    }

    /**
     * Returns the natural death rate of non-resistant cells <em>in
     * the absence of the therapeutic agent</em>.
     *
     * @return the natural death rate of non-resistant cells.
     */
    public double getNonResistantDeathRate() {
        return nonResistantDeathRate;
    }

    /**
     * Returns the rate constant for the migration of cells from site
     * to site.
     *
     * @return the rate constant for the migration of cells from site
     * to site.
     */
    public double getCellMigrationRate() {
        return cellMigrationRate;
    }

    /**
     * Returns the rate constant for the diffusion of the therapeutic
     * agent.
     *
     * @return the rate constant for the diffusion of the therapeutic
     * agent.
     */
    public double getDrugDiffusionRate() {
        return drugDiffusionRate;
    }

    /**
     * Returns the rate at which resistance mutations arise.
     *
     * @return the rate at which resistance mutations arise.
     */
    public double getResistanceMutationRate() {
        return resistanceMutationRate;
    }
}
