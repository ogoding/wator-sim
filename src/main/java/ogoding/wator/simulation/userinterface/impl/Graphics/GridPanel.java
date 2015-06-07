package ogoding.wator.simulation.userinterface.impl.Graphics;

import ogoding.wator.grid.cell.Cell;
import ogoding.wator.grid.cell.Fish;
import ogoding.wator.grid.cell.Shark;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OliverPC on 6/8/2015.
 */
public class GridPanel extends JPanel {
    private final Integer cellWidth;
    private final Integer cellHeight;
    private final Integer rows;
    private final Integer cols;

    private Cell[][] cellGrid;

    public GridPanel(Integer width, Integer height, Integer rows, Integer cols, Cell[][] cellGrid) {
        this.setPreferredSize(new Dimension(width, height));

        this.rows = rows;
        this.cols = cols;
        this.setLayout(new GridLayout(rows, cols));

        this.cellWidth = width / cols;
        this.cellHeight = height / rows;

        this.cellGrid = cellGrid;
    }

    public List<CellRectangle> createRectangleList(Cell[][] cells) {
        List<CellRectangle> squares = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                squares.add(new CellRectangle(j * cellWidth, i * cellHeight, cellWidth, cellHeight, cells[i][j]));
            }
        }

        return squares;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (CellRectangle rect : createRectangleList(cellGrid)) {
            g2.setColor(rect.getCell() instanceof Fish ? Color.GREEN :
                    rect.getCell() instanceof Shark ? Color.BLUE : Color.WHITE);
            g2.fillRect(rect.x + 2, rect.y + 2, rect.width - 4, rect.height - 4);
        }
    }

    public void setCellGrid(Cell[][] cellGrid) {
        this.cellGrid = cellGrid;
    }
}