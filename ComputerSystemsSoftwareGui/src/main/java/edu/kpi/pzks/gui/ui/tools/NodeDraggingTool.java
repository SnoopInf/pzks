package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.ui.panels.GraphPanel;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author Aloren
 */
public class NodeDraggingTool implements SelectionDraggingTool {

    private NodeView selectedNodeView;
    private Point mousePosition;
    private final GraphPanel graphPanel;

    public NodeDraggingTool(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        this.mousePosition = new Point(0, 0);
    }

    @Override
    public void paint(Graphics2D g2) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (graphPanel.getGraphView() != null) {
            beginDragging(me);
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        selectedNodeView = null;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (graphPanel.getSelectedNodeViews().contains(selectedNodeView)) {
            Point pp = graphPanel.getGrid().getSnapToGridPoint(me.getX(), me.getY());
            int dx = (int) (pp.x - mousePosition.x);
            int dy = (int) (pp.y - mousePosition.y);
            for (NodeView nodeView : graphPanel.getSelectedNodeViews()) {
                Point upperLeftCorner = nodeView.getUpperLeftCorner();
                Point p1 = graphPanel.getGrid().getSnapToGridPoint(upperLeftCorner.x + dx, upperLeftCorner.y + dy);
                nodeView.setUpperLeftCorner(p1);
            }
            mousePosition.x += dx;
            mousePosition.y += dy;
            graphPanel.checkSize();
            graphPanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        mousePosition = graphPanel.getGrid().getSnapToGridPoint(me.getX(), me.getY());
    }

    private void beginDragging(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        selectedNodeView = graphPanel.getGraphView().getNodeViewAtPoint(x, y);
        if (graphPanel.getSelectedNodeViews().contains(selectedNodeView)) {
            mousePosition = new Point(x, y);
        }
    }
}
