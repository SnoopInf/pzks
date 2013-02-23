package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.ui.panels.GraphPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Aloren
 */
public class NodeSelectionTool implements SelectionDraggingTool {

    protected final GraphPanel graphPanel;

    public NodeSelectionTool(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public void paint(Graphics2D g2) {
        for (NodeView selectedNodeView : graphPanel.getSelectedNodeViews()) {
            selectedNodeView.paint(g2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        NodeView nodeAtPoint = graphPanel.getGraphView().getNodeViewAtPoint(x, y);
        if (nodeAtPoint != null) {
            graphPanel.clearSelectedNodeViews();
            graphPanel.addSelectedNodeView(nodeAtPoint);
            if (me.getModifiers() == MouseEvent.BUTTON3_MASK) {
                JPopupMenu pp = nodeAtPoint.getPopupMenu();
                pp.show(me.getComponent(), me.getX(), me.getY());
            }
        } else {
            graphPanel.clearSelectedNodeViews();
        }
        graphPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
//        int x = me.getX();
//        int y = me.getY();
//        NodeView nodeViewAtPoint = graphPanel.getGraphView().getNodeViewAtPoint(x, y);
//        if (!isNodeSelected(nodeViewAtPoint)) {
//            setSelectedNodeView(nodeViewAtPoint);
//        }
//        graphPanel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

//    private boolean isNodeSelected(NodeView nodeViewAtPoint) {
//        final Set<NodeView> selectedNodeViews = graphPanel.getSelectedNodeViews();
//        return !(nodeViewAtPoint != null && !selectedNodeViews.contains(nodeViewAtPoint));
//    }

}
