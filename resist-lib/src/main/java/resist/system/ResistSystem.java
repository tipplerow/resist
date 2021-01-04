
package resist.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jam.math.JamRandom;
import jam.space.Site;
import jam.space.Space;
import jam.stoch.RateLink;
import jam.stoch.agent.AgentMap;
import jam.stoch.agent.AgentPopulation;
import jam.stoch.agent.AgentProc;
import jam.stoch.agent.AgentSystem;

import resist.agent.CellAgent;
import resist.agent.CellType;
import resist.agent.DrugAgent;
import resist.agent.DrugType;

/**
 * Encapsulates the stochastic agents and processes used to simulate
 * the evolution of therapeutic resistance.
 */
public final class ResistSystem extends AgentSystem {
    private final ResistParam param;

    private final Space space;
    private final List<Site> sites;

    private ResistSystem(ResistParam param) {
        this.param = param;
        this.space = param.getSpace();
        this.sites = new ArrayList<Site>(space.viewSites());
    }

    private void createAgents() {
        for (Site site : sites)
            createAgents(site);
    }

    private void createAgents(Site site) {
        for (CellType cellType : CellType.values())
            mapAgent(CellAgent.instance(site, cellType));

        for (DrugType drugType : DrugType.values())
            mapAgent(DrugAgent.instance(site, drugType));
    }

    private void assignPopulation(JamRandom rand) {
    }

    public static ResistSystem create() {
        return create(JamRandom.global(), ResistParam.global());
    }

    public static ResistSystem create(JamRandom rand, ResistParam param) {
        ResistSystem system = new ResistSystem(param);

        system.createAgents();
        system.assignPopulation(rand);

        return system;
    }
}
