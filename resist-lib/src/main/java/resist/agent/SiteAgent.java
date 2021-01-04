
package resist.agent;

import jam.space.Site;
import jam.stoch.agent.StochAgent;

/**
 * Represents a tumor cell or a therapeutic agent at a specific
 * spatial location.
 */
public abstract class SiteAgent extends StochAgent {
    /**
     * The spatial location of this agent.
     */
    protected final Site site;

    /**
     * The enumerated type for this agent.
     */
    protected final AgentType type;

    /**
     * Creates a new agent at a fixed spatial location.
     *
     * @param site the location of the agent.
     */
    protected SiteAgent(Site site, AgentType type) {
        this.site = site;
        this.type = type;
    }

    /**
     * Returns the stochastic agent with a specific type and
     * location.
     *
     * @param site the location of the stochastic agent.
     *
     * @param type the enumerated type of the stochastic agent.
     *
     * @return the stochastic agent having the specified type
     * and location.
     */
    public static SiteAgent instance(Site site, AgentType type) {
        if (type.isCell())
            return CellAgent.instance(site, (CellType) type);
        else
            return DrugAgent.instance(site, (DrugType) type);
    }

    /**
     * Returns the site where this agent is located.
     *
     * @return the site where this agent is located.
     */
    public Site getSite() {
        return site;
    }

    /**
     * Returns the enumerated type of this agent.
     *
     * @return the enumerated type of this agent.
     */
    public AgentType getType() {
        return type;
    }

    /**
     * Identifies cellular agents.
     *
     * @return {@code true} iff this agent is a tumor cell.
     */
    public boolean isCell() {
        return type.isCell();
    }

    /**
     * Identifies therapeutic agents.
     *
     * @return {@code true} iff this agent is a drug.
     */
    public boolean isDrug() {
        return type.isDrug();
    }

    @Override public String toString() {
        return String.format("%s(%s, %s)", getClass().getSimpleName(), site.getIndex(), type);
    }
}
