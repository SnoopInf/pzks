package edu.kpi.pzks.gui.actions.graph;

import edu.kpi.pzks.core.factory.GraphFactory;
import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.gui.layout.CircleLayout;
import edu.kpi.pzks.gui.modelview.GraphView;
import edu.kpi.pzks.gui.modelview.impl.GraphViewImpl;
import edu.kpi.pzks.gui.ui.panels.GraphPanel;
import edu.kpi.pzks.gui.ui.MainFrame;
import edu.kpi.pzks.gui.ui.panels.GraphGeneratorPanel;
import edu.kpi.pzks.gui.utils.STRINGS;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author AlorenNet
 */
public class GenTaskGraphAction extends AbstractAction {

    private final MainFrame mainFrame;

    public GenTaskGraphAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraphGeneratorPanel dialog = new GraphGeneratorPanel(mainFrame);
        dialog.setVisible(true);
        int result = JOptionPane.showConfirmDialog(mainFrame, dialog, STRINGS.GEN_GRAPH, JOptionPane.YES_NO_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            GraphPanel graphPanel = mainFrame.getTaskPanel();
            int numberOfNodes = dialog.getNumberOfNodes();
            int minNodeWeight = dialog.getMinNodeWeight();
            int maxNodeWeight = dialog.getMaxNodeWeight();
            double connectivity = dialog.getConnectivity();
            Graph generatedGraph = GraphFactory.newGraph(numberOfNodes, minNodeWeight, maxNodeWeight, connectivity);
            GraphView graphView = GraphViewImpl.createView(generatedGraph);
            Dimension prefSize = graphPanel.getPreferredSize();
            graphView.setBounds(new Rectangle(0, 0, (int) prefSize.getWidth(), (int) prefSize.getHeight()));
            graphView.layout(new CircleLayout());
            graphPanel.setGraphView(graphView);
            graphPanel.checkGraphIsValid();
            graphPanel.checkSize();
            graphPanel.repaint();
        }
    }
}
