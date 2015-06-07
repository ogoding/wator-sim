package ogoding.wator.simulation.userinterface.impl.Graphics;

import ogoding.wator.grid.cell.Cell;

import java.awt.*;

/**
 * Created by OliverPC on 6/8/2015.
 */
public class CellRectangle extends Rectangle {
    private final Cell cell;

    public CellRectangle(Integer x, Integer y, Integer width, Integer height, Cell cell) {
        super(x, y, width, height);
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public String toString() {
        return "x = " + x + ", y = " + y + ", width = " + width + ", height = " + height + ", cell class = " + (cell != null ? cell.getClass() : null);
    }
}