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

    public boolean gameOver() {
        return fishCount() <= 0 || sharkCount() <= 0;
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

    public void setIterationCount(Integer iterationCount) {
        this.iterationCount = iterationCount;
    }

    public void incrementIterationCount() {
        this.iterationCount++;
    }

    public static void main(String [ ] args) {
        try {
            WaTorSim sim = new WaTorSim();
            UserInterface userInterface = new ConsoleInterface();

            sim.setIterationCount(0);

            while (!sim.gameOver()) {
                userInterface.outputState(sim);
                sim.iterateGrid();
                sim.incrementIterationCount();
            }

            userInterface.outputResult(sim);
        } catch (IOException e) {
            System.out.println("Properties file not found. Exiting...");
        }
    }
}
