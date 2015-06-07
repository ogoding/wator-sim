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
    private final List<CellRectangle> squares = new ArrayList<>();

    public GridPanel(Integer width, Integer height, Integer rows, Integer cols) {
        this.setPreferredSize(new Dimension(width, height));

        this.rows = rows;
        this.cols = cols;
        this.setLayout(new GridLayout(rows, cols));

        this.cellWidth = width / cols;
        this.cellHeight = height / rows;
    }

    public void createRectangles(Cell[][] cells) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                createRectangle(j * cellWidth, i * cellHeight, cells[i][j]);
            }
        }
    }

    private void createRectangle(Integer x, Integer y, Cell cell) {
        squares.add(new CellRectangle(x, y, cellWidth, cellHeight, cell));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (CellRectangle rect : squares) {
            // TODO draw cell border

            g2.setColor(rect.getCell() instanceof Fish ? Color.BLUE :
                    rect.getCell() instanceof Shark ? Color.CYAN : Color.WHITE);
            g2.fillRect(rect.x, rect.y, rect.width, rect.height);
            //g2.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
    }
}