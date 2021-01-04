
package resist.agent;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import jam.space.Site;

/**
 * Represents a therapeutic agent at a specific spatial location.
 */
public final class DrugAgent extends SiteAgent {
    private static final Map<DrugType, Map<Site, DrugAgent>> instances;

    static {
        instances = new EnumMap<DrugType, Map<Site, DrugAgent>>(DrugType.class);

        for (DrugType type : DrugType.values())
            instances.put(type, new HashMap<Site, DrugAgent>());
    }

    private DrugAgent(Site site, DrugType type) {
        super(site, type);
    }

    /**
     * Returns the therapeutic agent for a specific location and drug
     * type.
     *
     * @param site the location of the drug.
     *
     * @param type the enumerated drug type.
     *
     * @return the therapeutic agent with the specific location and
     * type.
     */
    public static synchronized DrugAgent instance(Site site, DrugType type) {
        DrugAgent agent = instances.get(type).get(site);

        if (agent == null) {
            agent = new DrugAgent(site, type);
            instances.get(type).put(site, agent);
        }

        return agent;
    }

    /**
     * Returns the enumerated type for this agent.
     *
     * @return the enumerated type for this agent.
     */
    public DrugType getDrugType() {
        return (DrugType) type;
    }
}
