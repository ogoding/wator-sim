/**
 * Created by OliverPC on 6/7/2015.
 */
public abstract class Cell {
    private static int GENERATIONS_BEFORE_BIRTH;

    private int generationsSinceBirth = 0;
    private GridPos pos;

    public Cell(Integer generationsBeforeBirth, GridPos pos) {
        GENERATIONS_BEFORE_BIRTH = generationsBeforeBirth;
        this.pos = pos;
    }

    public abstract void move(WaTorGrid grid);

    public void spawn(WaTorGrid grid, GridPos pos) {
        if (shouldReproduce()) {
            spawnInternal(grid, pos);
        } else {
            incrementGenerationsSinceBirth();
        }
    }

    protected abstract void spawnInternal(WaTorGrid grid, GridPos pos);

    public void process(WaTorGrid grid) {
        GridPos originalPos = getPos();

        move(grid);
        spawn(grid, originalPos);
    }

    public void incrementGenerationsSinceBirth() {
        generationsSinceBirth++;
    }

    public boolean shouldReproduce() {
        return generationsSinceBirth >= GENERATIONS_BEFORE_BIRTH;
    }

    public GridPos getPos() {
        return pos;
    }

    public void setPos(GridPos pos) {
        this.pos = pos;
    }
}
