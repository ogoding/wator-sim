package ogoding.wator.simulation.userinterface.impl;

import ogoding.wator.simulation.WaTorSim;
import ogoding.wator.simulation.userinterface.UserInterface;

/**
 * Created by OliverPC on 6/7/2015.
 */
public class ConsoleInterface implements UserInterface {
    public void outputState(WaTorSim sim) {
        System.out.println(sim.getGrid().toString());
        System.out.println("IterationCount = [" + sim.getIterationCount() + "], Number of Fish = [" + sim.fishCount() + "], Number of Sharks = [" + sim.sharkCount() + "]");
    }

    public void outputResult(WaTorSim sim) {
        if (sim.fishCount() <= 0) {
            System.out.println(WaTorSim.prop.getFishWinMessage());
        } else if (sim.sharkCount() <= 0) {
            System.out.println(WaTorSim.prop.getSharksWinMessage());
        }
    }
}
