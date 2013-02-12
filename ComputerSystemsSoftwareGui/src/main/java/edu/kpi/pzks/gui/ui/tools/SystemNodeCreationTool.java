package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.modelview.impl.SystemNodeViewImpl;
import edu.kpi.pzks.gui.ui.GraphPanel;
import edu.kpi.pzks.gui.utils.CONSTANTS;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Aloren
 */
public class SystemNodeCreationTool extends NodeCreationTool {

    public SystemNodeCreationTool(GraphPanel graphPanel) {
        super(graphPanel);
    }

    @Override
    protected NodeView createNodeView(Point point) {
        NodeView nodeView = new SystemNodeViewImpl(new Node(), point.x, point.y);
        return nodeView;
    }

    @Override
    protected void fillShape(Graphics2D g2) {
        g2.fillRect(myMousePosition.x, myMousePosition.y,
                CONSTANTS.NODE_WIDTH, CONSTANTS.NODE_HEIGHT);
    }

    @Override
    protected void drawShape(Graphics2D g2) {
        g2.drawRect(myMousePosition.x, myMousePosition.y,
                CONSTANTS.NODE_WIDTH, CONSTANTS.NODE_HEIGHT);
    }
}
