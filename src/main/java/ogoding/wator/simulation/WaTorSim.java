package ogoding.wator.simulation;

import ogoding.wator.grid.GridUtils;
import ogoding.wator.grid.WaTorGrid;
import ogoding.wator.grid.cell.Fish;
import ogoding.wator.grid.cell.Shark;
import ogoding.wator.simulation.userinterface.ConsoleInterface;
import ogoding.wator.simulation.userinterface.UserInterface;

import java.io.IOException;

/**
 * Created by OliverPC on 6/7/2015.
 */
public class WaTorSim {

    public static WaTorSimProperties prop;
    private WaTorGrid grid;
    private Integer iterationCount;
    private Boolean active;

    public WaTorSim() throws IOException {
        prop = new WaTorSimProperties();
        grid = new WaTorGrid(prop.getGridRows(), prop.getGridColumns(), prop.getMaxFish(), prop.getMaxSharks());
    }

    public Integer fishCount() {
        return GridUtils.getCellCount(grid, Fish.class);
    }

    public Integer sharkCount() {
        return GridUtils.getCellCount(grid, Shark.class);
    }

    public void iterateGrid() {
        grid.iterateGrid();
    }

    public WaTorGrid getGrid() {
        return grid;
    }

    public Integer getIterationCount() {
        return iterationCount;
    }

    public void resetIterationCount() {
        this.iterationCount = 0;
    }

    public void incrementIterationCount() {
        this.iterationCount++;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public static void main(String [ ] args) {
        try {
            WaTorSim sim = new WaTorSim();
            UserInterface userInterface = new ConsoleInterface();

            sim.resetIterationCount();

            sim.setActive(true);

            while (sim.isActive()) {
                userInterface.outputState(sim);
                sim.iterateGrid();
                sim.incrementIterationCount();

                if (sim.fishCount() <= 0 || sim.sharkCount() <= 0) {
                    sim.setActive(false);
                }
            }

            userInterface.outputResult(sim);
        } catch (IOException e) {
            System.out.println("Properties file not found. Exiting...");
        }
    }
}
