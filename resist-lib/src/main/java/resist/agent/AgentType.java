
package resist.agent;

/**
 * Distinguishes between cellular agents and therapeutic agents.
 */
public interface AgentType {
    /**
     * Identifies cellular agents.
     *
     * @return {@code true} iff this agent is a tumor cell.
     */
    public abstract boolean isCell();

    /**
     * Identifies therapeutic agents.
     *
     * @return {@code true} iff this agent is a drug.
     */
    public abstract boolean isDrug();
}
