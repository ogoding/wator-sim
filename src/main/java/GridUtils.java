import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by OliverPC on 6/7/2015.
 */
public class GridUtils {
    public static GridPos createGridPos(Integer row, Integer col) {
        return new GridPos(row, col);
    }

    private static Map<GridPos, Cell> getNearbyCells(WaTorGrid grid, GridPos pos) {
        // TODO need to fix the x/y stuff
        Map<GridPos, Cell> result = new HashMap<>();

        for (int i = pos.getRow() - 1; i < (pos.getRow() + 2); i++) {
            for (int j = pos.getCol() - 1; j < (pos.getCol() + 2); j++) {
                if (!(i == pos.getRow() && j == pos.getCol())) {
                    GridPos gridPos = getNearbyPosition(i, j, grid.getNumOfRows(), grid.getNumOfCols());
                    if (gridPos != null) {
                        result.put(gridPos, grid.getCell(gridPos));
                    }
                }
            }
        }

        return result;
    }

    private static GridPos getNearbyPosition(Integer x, Integer y, Integer rowLimit, Integer colLimit) {
        if (0 <= x && x < rowLimit && 0 <= y && y < colLimit) {
            return createGridPos(x, y);
        } else {
            return null;
        }
    }

    public static List<GridPos> getNearbyEmptyCellPositions(WaTorGrid grid, GridPos pos) {
        return getNearbyCells(grid, pos)
                .entrySet().stream().filter(entry -> entry.getValue() == null)
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public static List<GridPos> getNearbyCellPositions(WaTorGrid grid, GridPos pos, Class clazz) {
        return getNearbyCells(grid, pos)
                .entrySet().stream().filter(entry -> clazz.isInstance(entry.getValue()))
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public static Integer getCellCount(WaTorGrid grid, Class clazz) {
        List<Cell> cellList = new ArrayList<>();

        for (Cell[] cellArray: grid.getGrid()) {
            for (Cell cell: cellArray) {
                if (clazz.isInstance(cell)) {
                    cellList.add(cell);
                }
            }
        }

        return cellList.size();
    }
}
