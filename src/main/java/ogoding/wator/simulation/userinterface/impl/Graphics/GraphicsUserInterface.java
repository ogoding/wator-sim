package ogoding.wator.simulation.userinterface.impl.Graphics;

import ogoding.wator.simulation.WaTorSim;
import ogoding.wator.simulation.userinterface.UserInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by OliverPC on 6/7/2015.
 */
public class GraphicsUserInterface implements UserInterface {
    private final JLabel stateLabel;
    private final GridPanel contentPanel;

    public GraphicsUserInterface(WaTorSim sim) {
        // Create the window
        JFrame f = new JFrame(WaTorSim.prop.getGUITitle());
        Integer windowWidth = WaTorSim.prop.getGUIWindowWidth();
        Integer windowHeight = WaTorSim.prop.getGUIWindowHeight();
        f.setPreferredSize(new Dimension(windowWidth, windowHeight));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new FlowLayout());
        // Add a label and a button
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(windowWidth, 30));
        stateLabel = new JLabel("Hello world!");
        titlePanel.add(stateLabel);

        contentPanel = new GridPanel(windowWidth - 20, windowHeight - 80, WaTorSim.prop.getGridRows(), WaTorSim.prop.getGridColumns());
        contentPanel.createRectangles(sim.getGrid().getGrid());

        f.add(titlePanel);
        f.add(contentPanel);
        // Arrange the components inside the window
        f.pack();
        // By default, the window is not visible. Make it visible.
        f.setVisible(true);
    }

    @Override
    public void outputState(WaTorSim sim) {
        stateLabel.setText("Active = " + sim.isActive() + ", Number of iterations = " + sim.getIterationCount());
        contentPanel.revalidate();
        contentPanel.repaint();
        System.out.println("sim.getGrid() = \n" + sim.getGrid());
    }

    @Override
    public void outputResult(WaTorSim sim) {
        if (sim.fishCount() <= 0) {
            stateLabel.setText(WaTorSim.prop.getFishWinMessage());
        } else if (sim.sharkCount() <= 0) {
            stateLabel.setText(WaTorSim.prop.getSharksWinMessage());
        }
        System.out.println("sim.getGrid() = \n" + sim.getGrid());
    }
}
