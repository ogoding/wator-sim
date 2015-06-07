import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by OliverPC on 6/7/2015.
 */
public class WaTorSimProperties {
    public Integer GRID_ROWS;
    public Integer GRID_COLUMNS;
    public Integer MAX_FISH;
    public Integer MAX_SHARKS;
    public Integer INITIAL_SHARK_ENERGY;
    public Integer SHARK_INC;
    public Integer SHARK_DEC;
    public Integer GENERATIONS_BEFORE_REPRODUCE_FISH;
    public Integer GENERATIONS_BEFORE_REPRODUCE_SHARK;

    private Properties properties;

    public WaTorSimProperties() throws IOException {
        loadPropertiesFile();
        loadProperties();
    }

    private void loadProperties() {
        GRID_ROWS = Integer.parseInt(properties.getProperty("grid.rows"));
        GRID_COLUMNS = Integer.parseInt(properties.getProperty("grid.columns"));
        MAX_FISH = Integer.parseInt(properties.getProperty("grid.sharks.maximum"));
        MAX_SHARKS = Integer.parseInt(properties.getProperty("grid.fish.maximum"));

        INITIAL_SHARK_ENERGY = Integer.parseInt(properties.getProperty("cell.shark.energy.initial"));
        SHARK_INC = Integer.parseInt(properties.getProperty("cell.shark.energy.increase"));
        SHARK_DEC = Integer.parseInt(properties.getProperty("cell.shark.energy.decrease"));
        GENERATIONS_BEFORE_REPRODUCE_FISH = Integer.parseInt(properties.getProperty("cell.shark.generations.before.reproduce"));
        GENERATIONS_BEFORE_REPRODUCE_SHARK = Integer.parseInt(properties.getProperty("cell.fish.generations.before.reproduce"));
    }

    public void loadPropertiesFile() throws IOException {
        properties = new Properties();
        String propertyFileName = "config.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("Property file " + propertyFileName + " was not found in the classpath");
        }
    }

    public Integer getGridRows() {
        return GRID_ROWS;
    }

    public Integer getGridColumns() {
        return GRID_COLUMNS;
    }

    public Integer getMaxFish() {
        return MAX_FISH;
    }

    public Integer getMaxSharks() {
        return MAX_SHARKS;
    }

    public Integer getInitialSharkEnergy() {
        return INITIAL_SHARK_ENERGY;
    }

    public Integer getSharkInc() {
        return SHARK_INC;
    }

    public Integer getSharkDec() {
        return SHARK_DEC;
    }

    public Integer getGenerationsBeforeReproduceFish() {
        return GENERATIONS_BEFORE_REPRODUCE_FISH;
    }

    public Integer getGenerationsBeforeReproduceShark() {
        return GENERATIONS_BEFORE_REPRODUCE_SHARK;
    }
}
