import java.io.IOException;

/**
 * Created by OliverPC on 6/7/2015.
 */
public class WaTorSim {

    public static WaTorSimProperties prop;
    private WaTorGrid grid;

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

    public static void main(String [ ] args) {
        try {
            WaTorSim sim = new WaTorSim();

            int iterationCount = 0;

            System.out.println(sim.getGrid().toString());
            System.out.println("IterationCount = [" + iterationCount + "], Number of Fish = [" + sim.fishCount() + "], Number of Sharks = [" + sim.sharkCount() + "]");

            while (!sim.gameOver()) {
                sim.iterateGrid();
                iterationCount++;
                System.out.println(sim.getGrid().toString());
                System.out.println("IterationCount = [" + iterationCount + "], Number of Fish = [" + sim.fishCount() + "], Number of Sharks = [" + sim.sharkCount() + "]");
            }

            if (sim.fishCount() <= 0) {
                System.out.println("Sharks ate all the fish!");
            } else if (sim.sharkCount() <= 0) {
                System.out.println("Sharks all died out!");
            }
        } catch (IOException e) {
            System.out.println("Properties file not found. Exiting...");
        }
    }
}
