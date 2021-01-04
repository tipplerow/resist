
package resist.agent;

import jam.bravais.UnitIndex;
import jam.math.Point;
import jam.space.Site;

import org.junit.*;
import static org.junit.Assert.*;

public final class SiteAgentTest {
    private static final Point point1 = Point.at(1.0);
    private static final Point point2 = Point.at(2.0);

    private static final UnitIndex index1 = UnitIndex.at(1);
    private static final UnitIndex index2 = UnitIndex.at(2);

    private static final Site site1 = Site.create(index1, point1);
    private static final Site site2 = Site.create(index2, point2);

    private static final SiteAgent cell1N  = SiteAgent.instance(site1, CellType.NON_RESISTANT);
    private static final SiteAgent cell1A  = SiteAgent.instance(site1, CellType.RESISTANT_A);
    private static final SiteAgent cell1B  = SiteAgent.instance(site1, CellType.RESISTANT_B);
    private static final SiteAgent cell1AB = SiteAgent.instance(site1, CellType.RESISTANT_AB);

    private static final SiteAgent cell2N  = SiteAgent.instance(site2, CellType.NON_RESISTANT);
    private static final SiteAgent cell2A  = SiteAgent.instance(site2, CellType.RESISTANT_A);
    private static final SiteAgent cell2B  = SiteAgent.instance(site2, CellType.RESISTANT_B);
    private static final SiteAgent cell2AB = SiteAgent.instance(site2, CellType.RESISTANT_AB);

    private static final SiteAgent drug1A = SiteAgent.instance(site1, DrugType.A);
    private static final SiteAgent drug1B = SiteAgent.instance(site1, DrugType.B);

    private static final SiteAgent drug2A = SiteAgent.instance(site2, DrugType.A);
    private static final SiteAgent drug2B = SiteAgent.instance(site2, DrugType.B);

    private void assertAttributes(SiteAgent agent, Site site, AgentType type, boolean isCell) {
        assertEquals(site, agent.getSite());
        assertEquals(type, agent.getType());
        assertEquals(isCell, agent.isCell());
        assertEquals(!isCell, agent.isDrug());
    }

    private void assertResistance(SiteAgent agent, boolean resA, boolean resB) {
        assertEquals(resA, ((CellAgent) agent).isResistant(DrugType.A));
        assertEquals(resB, ((CellAgent) agent).isResistant(DrugType.B));
    }

    @Test public void testAttributes() {
        assertAttributes(cell1N,  site1, CellType.NON_RESISTANT, true);
        assertAttributes(cell1A,  site1, CellType.RESISTANT_A,   true);
        assertAttributes(cell1B,  site1, CellType.RESISTANT_B,   true);
        assertAttributes(cell1AB, site1, CellType.RESISTANT_AB,  true);

        assertAttributes(cell2N,  site2, CellType.NON_RESISTANT, true);
        assertAttributes(cell2A,  site2, CellType.RESISTANT_A,   true);
        assertAttributes(cell2B,  site2, CellType.RESISTANT_B,   true);
        assertAttributes(cell2AB, site2, CellType.RESISTANT_AB,  true);

        assertAttributes(drug1A, site1, DrugType.A, false);
        assertAttributes(drug1B, site1, DrugType.B, false);

        assertAttributes(drug2A, site2, DrugType.A, false);
        assertAttributes(drug2B, site2, DrugType.B, false);
    }

    @Test public void testResistance() {
        assertResistance(cell1N,  false, false);
        assertResistance(cell1A,  true,  false);
        assertResistance(cell1B,  false, true);
        assertResistance(cell1AB, true,  true);

        assertResistance(cell2N,  false, false);
        assertResistance(cell2A,  true,  false);
        assertResistance(cell2B,  false, true);
        assertResistance(cell2AB, true,  true);
    }

    @Test public void testPhysicalEquality() {
        SiteAgent agentX = SiteAgent.instance(site1, CellType.RESISTANT_A);
        SiteAgent agentY = SiteAgent.instance(site1, CellType.RESISTANT_A);

        assertTrue(agentX == agentY);
        assertEquals(System.identityHashCode(agentX), System.identityHashCode(agentY));
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("resist.agent.SiteAgentTest");
    }
}
