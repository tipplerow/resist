
package resist.system;

import org.junit.*;
import static org.junit.Assert.*;

public abstract class ResistTestBase {
    static {
        System.setProperty(ResistParam.LATTICE_PROPERTY, "SQUARE; 1.0; 3, 3");
        System.setProperty(ResistParam.SITE_CAPACITY_PROPERTY, "100");
        System.setProperty(ResistParam.INITIAL_CELL_COUNT_PROPERTY, "800");
        System.setProperty(ResistParam.INITIAL_DRUG_COUNT_PROPERTY, "200");
        System.setProperty(ResistParam.DRUG_DECAY_RATE_PROPERTY, "0.01");
        System.setProperty(ResistParam.DRUG_UPTAKE_RATE_PROPERTY, "0.01");
        System.setProperty(ResistParam.RESISTANT_BIRTH_RATE_PROPERTY, "1.05");
        System.setProperty(ResistParam.RESISTANT_DEATH_RATE_PROPERTY, "1.0");
        System.setProperty(ResistParam.NON_RESISTANT_BIRTH_RATE_PROPERTY, "1.1");
        System.setProperty(ResistParam.NON_RESISTANT_DEATH_RATE_PROPERTY, "1.0");
        System.setProperty(ResistParam.CELL_MIGRATION_RATE_PROPERTY, "0.1");
        System.setProperty(ResistParam.DRUG_DIFFUSION_RATE_PROPERTY, "0.1");
        System.setProperty(ResistParam.RESISTANCE_MUTATION_RATE_PROPERTY, "0.001");
    }
}
