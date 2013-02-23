/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kpi.pzks.gui.ui.dialogs;

import edu.kpi.pzks.gui.ui.MainFrame;
import edu.kpi.pzks.gui.utils.STRINGS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author AlorenNet
 */
public class GraphGeneratorDialog extends JDialog {

    private final MainFrame mainFrame;
    private final JSpinner numberOfNodesSpinner;
    private final JSpinner minWeightSpinner;
    private final JSpinner maxWeightSpinner;
    private final JSpinner connectivitySpinner;

    public GraphGeneratorDialog(MainFrame mainFrame) {
        super(mainFrame, STRINGS.GEN_GRAPH);
        this.mainFrame = mainFrame;

        JLabel numberOfNodesLabel = new JLabel();
        JLabel minWeightLabel = new JLabel();
        JLabel maxWeightLabel = new JLabel();
        JLabel connectivityLabel = new JLabel();

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
                minWeightSpinnerModel.setMaximum((Integer) maxWeightSpinner.getValue());
            }
        });
        
        double curConnectivity = 0.5;
        double minConnectivity = 0.0;
        double maxConnectivity = 1.0;
        double connectivityStep = 0.1;
        SpinnerNumberModel connectivitySpinnerModel = new SpinnerNumberModel(curConnectivity, minConnectivity, maxConnectivity, connectivityStep);
        this.connectivitySpinner = new JSpinner(connectivitySpinnerModel);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLocationRelativeTo(mainFrame);
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
        return (Integer) connectivitySpinner.getValue();
    }
}
