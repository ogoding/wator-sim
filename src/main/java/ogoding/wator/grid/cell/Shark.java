package ogoding.wator.grid.cell;

import ogoding.wator.simulation.WaTorSim;
import ogoding.wator.grid.GridUtils;
import ogoding.wator.grid.WaTorGrid;

import java.util.List;
import java.util.Random;

/**
 * Created by OliverPC on 6/7/2015.
 */
public class Shark extends Cell {
    private static final int ENERGY_INC = WaTorSim.prop.getSharkInc();
    private static final int ENERGY_DEC = WaTorSim.prop.getSharkDec();

    private int energy;

    public Shark(Integer startingEnergy, GridPos pos) {
        super(WaTorSim.prop.getGenerationsBeforeReproduceShark(), pos);
        this.energy = startingEnergy;
    }

    @Override
    public void move(WaTorGrid grid) {
        GridPos originalPos = getPos();

        List<GridPos> nearbyEmptyCells = GridUtils.getNearbyEmptyCellPositions(grid, originalPos);
        if (nearbyEmptyCells.isEmpty()) {
            nearbyEmptyCells = GridUtils.getNearbyCellPositions(grid, originalPos, Fish.class);
        }

        if (!nearbyEmptyCells.isEmpty()) {
            GridPos newPos = nearbyEmptyCells.get(new Random().nextInt(nearbyEmptyCells.size()));

            if (grid.getCell(newPos) instanceof Fish) {
                incrementEnergy();
            }

            setPos(newPos);
            grid.setCell(newPos, this);
            grid.killCell(originalPos);
        }
    }

    @Override
    protected void spawnInternal(WaTorGrid grid, GridPos pos) {
        grid.spawnCell(pos, CellType.SHARK);
    }

    @Override
    public void process(WaTorGrid grid) {
        GridPos originalPos = getPos();

        decrementEnergy();

        if (getEnergy() <= 0) {
            grid.killCell(originalPos);
        } else {
            move(grid);
            spawn(grid, originalPos);
        }
    }

    public int getEnergy() {
        return energy;
    }

    public void incrementEnergy() {
        energy += ENERGY_INC;
    }

    public void decrementEnergy() {
        energy -= ENERGY_DEC;
    }
}
