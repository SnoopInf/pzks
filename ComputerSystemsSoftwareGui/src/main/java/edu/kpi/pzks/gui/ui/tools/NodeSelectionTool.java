package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.ui.GraphPanel;
import edu.kpi.pzks.gui.ui.GraphPanel.NodeType;
import edu.kpi.pzks.gui.utils.COLORS;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;

/**
 *
 * @author Aloren
 */
public class NodeSelectionTool implements SelectionDraggingTool {

    protected final GraphPanel graphPanel;
    
    public static Tool newNodeSelectionTool(GraphPanel graphPanel) {
        final NodeType type = graphPanel.getType();
        if(type.equals(NodeType.System)) {
            return new SystemNodeSelectionTool(graphPanel);
        } else if(type.equals(NodeType.Task)) {
            return new NodeSelectionTool(graphPanel);
        }
        throw new IllegalArgumentException("Not supported type of panel: "+type);
    }

    protected NodeSelectionTool(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public void paint(Graphics2D g2) {
        paintSelectedNodeViews(g2);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        NodeView selectedNodeView = graphPanel.getGraphView().getNodeViewAtPoint(x, y);
        if (selectedNodeView != null) {
            setSelectedNodeView(selectedNodeView);
            if (me.getModifiers() == MouseEvent.BUTTON3_MASK) {
                JPopupMenu pp = selectedNodeView.getPopupMenu();
                pp.show(me.getComponent(), me.getX(), me.getY());
            }
        } else {
            clearSelected();
        }
        graphPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        NodeView nodeViewAtPoint = graphPanel.getGraphView().getNodeViewAtPoint(x, y);
        if (!isNodeSelected(nodeViewAtPoint)) {
            setSelectedNodeView(nodeViewAtPoint);
        }
        graphPanel.repaint();
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

    private void setSelectedNodeView(NodeView selectedNodeView) {
        clearSelected();
        addToSelected(selectedNodeView);
    }

    private void clearSelected() {
        graphPanel.getSelectedNodeViews().clear();
    }

    private boolean isNodeSelected(NodeView nodeViewAtPoint) {
        return !(nodeViewAtPoint != null
                && !graphPanel.getSelectedNodeViews().contains(nodeViewAtPoint));
    }

    private void addToSelected(NodeView nodeViewAtPoint) {
        graphPanel.getSelectedNodeViews().add(nodeViewAtPoint);
    }

    private void paintSelectedNodeViews(Graphics2D g2) {
        for (NodeView selectedNodeView : graphPanel.getSelectedNodeViews()) {
            paintSelectedNode(g2, selectedNodeView);
        }
    }

    private void paintSelectedNode(Graphics2D g2, NodeView selectedNodeView) {
        paintSelectedNodeBorder(g2, selectedNodeView);
        paintSelectedNodeInside(g2, selectedNodeView);
    }

    private void paintSelectedNodeBorder(Graphics2D g2, NodeView selectedNodeView) {
        g2.setStroke(new BasicStroke(3));
        g2.setColor(COLORS.NODE_BORDER_SELECTED_COLOR);
        drawShape(g2, selectedNodeView);
    }

    private void paintSelectedNodeInside(Graphics2D g2, NodeView selectedNodeView) {
        g2.setColor(COLORS.FILL_BLUE);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 6 * 0.1f));
        fillShape(g2, selectedNodeView);
    }

    protected void drawShape(Graphics2D g2, NodeView selectedNodeView) {
        g2.drawOval(selectedNodeView.getUpperLeftCorner().x,
                selectedNodeView.getUpperLeftCorner().y,
                selectedNodeView.getWidth(),
                selectedNodeView.getHeight());
    }

    protected void fillShape(Graphics2D g2, NodeView selectedNodeView) {
        g2.fillOval(selectedNodeView.getUpperLeftCorner().x,
                selectedNodeView.getUpperLeftCorner().y,
                selectedNodeView.getWidth(),
                selectedNodeView.getHeight());
    }
    
}
