package edu.kpi.pzks.gui.ui.dialogs;

import edu.kpi.pzks.gui.ui.MainFrame;
import edu.kpi.pzks.gui.utils.STRINGS;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author asmirnova
 */
public class GraphGeneratorPanel extends JPanel {
    
    private final MainFrame mainFrame;
    private final JSpinner numberOfNodesSpinner;
    private final JSpinner minWeightSpinner;
    private final JSpinner maxWeightSpinner;
    private final JSpinner connectivitySpinner;
    
    public GraphGeneratorPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        JLabel numberOfNodesLabel = new JLabel(STRINGS.NUM_NODES);
        JLabel minWeightLabel = new JLabel(STRINGS.MIN_WEIGHT);
        JLabel maxWeightLabel = new JLabel(STRINGS.MAX_WEIGHT);
        JLabel connectivityLabel = new JLabel(STRINGS.CONNECTIVITY);
        
        int value = 4;
        int min = 2;
        int max = 50;
        int step = 1;
        SpinnerNumberModel numberOfNodesSpinnerModel = new SpinnerNumberModel(value, min, max, step);
        this.numberOfNodesSpinner = new JSpinner(numberOfNodesSpinnerModel);
        
        int curMinWeight = 1;
        int minWeight = 1;
        int maxWeight = 10;
        final SpinnerNumberModel minWeightSpinnerModel = new SpinnerNumberModel(curMinWeight, minWeight, maxWeight, step);
        this.minWeightSpinner = new JSpinner(minWeightSpinnerModel);
        
        int curMaxWeight = 100;
        SpinnerNumberModel maxWeightSpinnerModel = new SpinnerNumberModel(maxWeight, minWeight, curMaxWeight, step);
        this.maxWeightSpinner = new JSpinner(maxWeightSpinnerModel);
        maxWeightSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                final int curMaxValue = (Integer) maxWeightSpinner.getValue();
                final int curMinValue = (Integer) minWeightSpinner.getValue();
                if (curMinValue > curMaxValue) {
                    minWeightSpinner.setValue(curMaxValue);
                }
                minWeightSpinnerModel.setMaximum(curMaxValue);
            }
        });
        
        double curConnectivity = 0.5;
        double minConnectivity = 0.0;
        double maxConnectivity = 1.0;
        double connectivityStep = 0.1;
        SpinnerNumberModel connectivitySpinnerModel = new SpinnerNumberModel(curConnectivity, minConnectivity, maxConnectivity, connectivityStep);
        this.connectivitySpinner = new JSpinner(connectivitySpinnerModel);
        
        setLayout(new GridLayout(3, 1, 1, 1));
        JPanel numNodesPanel = new JPanel(new GridLayout(1, 2));
        numNodesPanel.add(numberOfNodesLabel);
        numNodesPanel.add(numberOfNodesSpinner);
        add(numNodesPanel);
        
        JPanel weightPanel = new JPanel();
        weightPanel.setBorder(BorderFactory.createTitledBorder(STRINGS.NODE_WEIGHT));
        weightPanel.setLayout(new GridLayout(2, 2, 10, 10));
        weightPanel.add(minWeightLabel);
        weightPanel.add(minWeightSpinner);
        weightPanel.add(maxWeightLabel);
        weightPanel.add(maxWeightSpinner);
        add(weightPanel);
        
        JPanel connectivityPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        connectivityPanel.add(connectivityLabel);
        connectivityPanel.add(connectivitySpinner);
        add(connectivityPanel);
    }
    
    public int getNumberOfNodes() {
        return (Integer) numberOfNodesSpinner.getValue();
    }
    
    public int getMinNodeWeight() {
        return (Integer) minWeightSpinner.getValue();
    }
    
    public int getMaxNodeWeight() {
        return (Integer) maxWeightSpinner.getValue();
    }
    
    public double getConnectivity() {
        return (Double) connectivitySpinner.getValue();
    }
}
