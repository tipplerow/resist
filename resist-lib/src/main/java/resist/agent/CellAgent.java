
package resist.agent;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import jam.space.Site;

/**
 * Represents a tumor cell at a specific spatial location.
 */
public final class CellAgent extends SiteAgent {
    private static final Map<CellType, Map<Site, CellAgent>> instances;

    static {
        instances = new EnumMap<CellType, Map<Site, CellAgent>>(CellType.class);

        for (CellType type : CellType.values())
            instances.put(type, new HashMap<Site, CellAgent>());
    }

    private CellAgent(Site site, CellType type) {
        super(site, type);
    }

    /**
     * Returns the cellular agent for a specific location and
     * resistance phenotype.
     *
     * @param site the location of the cellular agent.
     *
     * @param type the resistance phenotype of the agent.
     *
     * @return the cellular agent with the specific location and
     * phenotype.
     */
    public static synchronized CellAgent instance(Site site, CellType type) {
        CellAgent agent = instances.get(type).get(site);

        if (agent == null) {
            agent = new CellAgent(site, type);
            instances.get(type).put(site, agent);
        }

        return agent;
    }

    /**
     * Returns the enumerated type for this agent.
     *
     * @return the enumerated type for this agent.
     */
    public CellType getCellType() {
        return (CellType) type;
    }

    /**
     * Identifies drugs to which this cellular agent is resistant.
     *
     * @param drug a drug of interest.
     *
     * @return {@code true} iff this cellular agent is resistant to
     * the specified drug.
     */
    public boolean isResistant(DrugType drug) {
        return getCellType().isResistant(drug);
    }
}
