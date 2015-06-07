package ogoding.wator.grid.cell;

/**
 * Created by OliverPC on 6/7/2015.
 */
public class GridPos {
    private final int row;
    private final int col;

    public GridPos(Integer row, Integer col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "x = [" + row + "], y = [" + col + "]";
    }
}