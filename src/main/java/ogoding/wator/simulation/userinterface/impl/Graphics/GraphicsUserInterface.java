package ogoding.wator.simulation.userinterface.impl.Graphics;

import ogoding.wator.simulation.WaTorSim;
import ogoding.wator.simulation.userinterface.UserInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by OliverPC on 6/7/2015.
 */
public class GraphicsUserInterface implements UserInterface {
    private final JFrame frame;
    private final JLabel stateLabel;
    private final GridPanel contentPanel;

    public GraphicsUserInterface(WaTorSim sim) {
        frame = new JFrame(WaTorSim.prop.getGUITitle());
        Integer windowWidth = WaTorSim.prop.getGUIWindowWidth();
        Integer windowHeight = WaTorSim.prop.getGUIWindowHeight();

        System.out.println("windowHeight = " + windowHeight);
        System.out.println("windowWidth = " + windowWidth);

        frame.setPreferredSize(new Dimension(windowWidth, windowHeight));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        // Add a label and a button
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(windowWidth, 30));
        stateLabel = new JLabel("Hello world!");
        titlePanel.add(stateLabel);

        contentPanel = new GridPanel(windowWidth - 20, windowHeight - 80, WaTorSim.prop.getGridRows(), WaTorSim.prop.getGridColumns(), sim.getGrid());

        frame.add(titlePanel);
        frame.add(contentPanel);
        // Arrange the components inside the window
        frame.pack();
        // By default, the window is not visible. Make it visible.
        frame.setVisible(true);
    }

    @Override
    public void outputState(WaTorSim sim) {
        stateLabel.setText("Active = " + sim.isActive() + ", Number of iterations = " + sim.getIterationCount());
        contentPanel.revalidate();
        contentPanel.repaint();
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
