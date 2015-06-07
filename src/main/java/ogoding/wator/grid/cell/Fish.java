package ogoding.wator.grid.cell;

import ogoding.wator.simulation.WaTorSim;
import ogoding.wator.grid.GridUtils;
import ogoding.wator.grid.WaTorGrid;

import java.util.List;
import java.util.Random;

/**
 * Created by OliverPC on 6/7/2015.
 */
public class Fish extends Cell {

    public Fish(GridPos pos) {
        super(WaTorSim.prop.getGenerationsBeforeReproduceFish(), pos);
    }

    @Override
    public void move(WaTorGrid grid) {
        GridPos originalPos = getPos();

        List<GridPos> nearbyEmptyCells = GridUtils.getNearbyEmptyCellPositions(grid, originalPos);

        if (!nearbyEmptyCells.isEmpty()) {
            Integer option = new Random().nextInt(nearbyEmptyCells.size());
            GridPos newPos = nearbyEmptyCells.get(option);

            setPos(newPos);
            grid.setCell(newPos, this);
            grid.killCell(originalPos);
        }
    }

    @Override
    protected void spawnInternal(WaTorGrid grid, GridPos pos) {
        grid.spawnCell(pos, CellType.FISH);
    }
}
