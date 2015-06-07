package ogoding.wator.grid;

import ogoding.wator.grid.cell.*;
import ogoding.wator.simulation.WaTorSim;

import java.util.Random;

/**
 * Created by OliverPC on 6/7/2015.
 */
public class WaTorGrid {
    private final Cell[][] grid;
    private final Integer numOfRows;
    private final Integer numOfCols;

    public WaTorGrid(Integer numOfRows, Integer numOfCols, Integer maxFish, Integer maxSharks) {
        this.numOfRows = numOfRows;
        this.numOfCols = numOfCols;

        grid = new Cell[numOfRows][numOfCols];
        initializeGrid(numOfRows, numOfCols, maxFish, maxSharks);
    }

    private void initializeGrid(Integer numOfRows, Integer numOfCols, Integer maxFish, Integer maxSharks) {
        // TODO does this need to be seeded?
        Random random = new Random();

        Integer fishCount = 0;
        Integer sharkCount = 0;

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                Integer tempCount = initializeCell(fishCount, maxFish, random, GridUtils.createGridPos(i, j), CellType.FISH);
                // If the number of fish doesn't change then we need to generate a shark
                if (tempCount.equals(fishCount)) {
                    sharkCount = initializeCell(sharkCount, maxSharks, random, GridUtils.createGridPos(i, j), CellType.SHARK);
                }
                fishCount = tempCount;
            }
        }

    }

    private Integer initializeCell(Integer currentCount, Integer limit, Random randomGen, GridPos pos, CellType type) {
        Integer count = currentCount;
        // TODO does this need to be different than 10%?
        if (count < limit && randomGen.nextInt(10) == 1) {
            count++;
            spawnCell(pos, type);
        }

        return count;
    }

    public void iterateGrid() {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                processCell(getCell(i, j));
            }
        }
    }

    private void processCell(Cell cell) {
        if (cell != null) {
            cell.process(this);
        }
    }

    public void spawnCell(GridPos pos, CellType type) {
        Cell newCell = null;

        if (CellType.FISH.equals(type)) {
            newCell = new Fish(pos);
        } else if (CellType.SHARK.equals(type)) {
            newCell = new Shark(WaTorSim.prop.getInitialSharkEnergy(), pos);
        }

        setCell(pos, newCell);
    }

    public Cell getCell(Integer row, Integer col) {
        return grid[row][col];
    }

    public Cell getCell(GridPos pos) {
        return grid[pos.getRow()][pos.getCol()];
    }

    public void setCell(GridPos pos, Cell newCell) {
        grid[pos.getRow()][pos.getCol()] = newCell;
    }

    public void killCell(GridPos pos) {
        setCell(pos, null);
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Integer getNumOfRows() {
        return numOfRows;
    }

    public Integer getNumOfCols() {
        return numOfCols;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                output.append("[");
                Cell cell = grid[i][j];
                output.append(cell instanceof Fish ? "F" : cell instanceof Shark ? "S" : " ");
                output.append("]" );
            }
            output.append("\n");
        }

        return output.toString();
    }
}
