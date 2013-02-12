package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.ui.GraphPanel;
import java.awt.Graphics2D;

/**
 *
 * @author Aloren
 */
public class SystemNodeSelectionTool extends NodeSelectionTool {

    protected SystemNodeSelectionTool(GraphPanel graphPanel) {
        super(graphPanel);
    }

    @Override
    protected void drawShape(Graphics2D g2, NodeView selectedNodeView) {
        g2.drawRect(selectedNodeView.getUpperLeftCorner().x,
                selectedNodeView.getUpperLeftCorner().y,
                selectedNodeView.getWidth(),
                selectedNodeView.getHeight());
    }

    @Override
    protected void fillShape(Graphics2D g2, NodeView selectedNodeView) {
        g2.fillRect(selectedNodeView.getUpperLeftCorner().x,
                selectedNodeView.getUpperLeftCorner().y,
                selectedNodeView.getWidth(),
                selectedNodeView.getHeight());
    }
}
