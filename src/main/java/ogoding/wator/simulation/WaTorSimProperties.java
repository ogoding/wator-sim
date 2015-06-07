package ogoding.wator.simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by OliverPC on 6/7/2015.
 */
public class WaTorSimProperties {
    private Properties properties;

    public WaTorSimProperties() throws IOException {
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
        return Integer.parseInt(properties.getProperty("grid.rows"));
    }

    public Integer getGridColumns() {
        return Integer.parseInt(properties.getProperty("grid.columns"));
    }

    public Integer getMaxFish() {
        return Integer.parseInt(properties.getProperty("grid.fish.maximum"));
    }

    public Integer getMaxSharks() {
        return Integer.parseInt(properties.getProperty("grid.sharks.maximum"));
    }

    public Integer getInitialSharkEnergy() {
        return Integer.parseInt(properties.getProperty("cell.shark.energy.initial"));
    }

    public Integer getSharkInc() {
        return Integer.parseInt(properties.getProperty("cell.shark.energy.increase"));
    }

    public Integer getSharkDec() {
        return Integer.parseInt(properties.getProperty("cell.shark.energy.decrease"));
    }

    public Integer getGenerationsBeforeReproduceFish() {
        return Integer.parseInt(properties.getProperty("cell.shark.generations.before.reproduce"));
    }

    public Integer getGenerationsBeforeReproduceShark() {
        return Integer.parseInt(properties.getProperty("cell.fish.generations.before.reproduce"));
    }

    public String getSharksWinMessage() {
        return properties.getProperty("sim.win.sharks");
    }

    public String getFishWinMessage() {
        return properties.getProperty("sim.win.fish");
    }

    public String getGUITitle() {
        return properties.getProperty("gui.window.title");
    }

    public Integer getGUIWindowWidth() {
        return Integer.parseInt(properties.getProperty("gui.window.width"));
    }

    public Integer getGUIWindowHeight() {
        return Integer.parseInt(properties.getProperty("gui.window.height"));
    }
}
