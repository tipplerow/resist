
package resist.agent;

/**
 * Enumerates the therapeutic agents in a stochastic simulation.
 */
public enum DrugType implements AgentType {
    A, B;

    @Override public boolean isCell() {
        return false;
    }

    @Override public boolean isDrug() {
        return true;
    }
}
