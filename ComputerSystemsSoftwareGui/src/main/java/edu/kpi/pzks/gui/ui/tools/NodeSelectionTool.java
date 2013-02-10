package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.ui.GraphPanel;
import edu.kpi.pzks.gui.utils.COLORS;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author Aloren
 */
public class NodeSelectionTool implements SelectionDraggingTool {

    private final GraphPanel graphPanel;
    private Point selectionStartPoint;
    private Point mousePosition = new Point();

    public NodeSelectionTool(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public void paint(Graphics2D g2) {
        paintSelectedNodeViews(g2);
        if (isRectangleSelect()) {
            paintRectangleSelection(g2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        NodeView selectedNodeView = graphPanel.getGraphView().getNodeViewAtPoint(x, y);
        if (selectedNodeView != null) {
            setSelectedNodeView(selectedNodeView);
            graphPanel.repaint();
//            if (me.getModifiers() == MouseEvent.BUTTON3_MASK) {
//                JPopupMenu pp = selectedNodeView.getPopupMenu();
//                pp.show(me.getComponent(), me.getX(), me.getY());
//            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        NodeView nodeViewAtPoint = graphPanel.getGraphView().getNodeViewAtPoint(x, y);
        if (!isNodeSelected(nodeViewAtPoint)) {
            setSelectedNodeView(nodeViewAtPoint);
        } else if (isNothingSelected(nodeViewAtPoint, x, y)) {
            beginRectangleSelection(x, y);
        }
        graphPanel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        finishRectangleSelect();
        graphPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        snapToGrid(me);
        if (isRectangleSelect()) {
            addNodeViewsToRectangle();
            graphPanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        snapToGrid(me);
    }

    private void beginRectangleSelection(int x, int y) {
        clearSelected();
        selectionStartPoint = new Point(x, y);
    }

    private void snapToGrid(MouseEvent me) {
        mousePosition = graphPanel.getGrid().getSnapToGridPoint(me.getX(), me.getY());
    }

    private void finishRectangleSelect() {
        selectionStartPoint = null;
    }

    private void setSelectedNodeView(NodeView selectedNodeView) {
        clearSelected();
        addToSelected(selectedNodeView);
    }

    private boolean isRectangleSelect() {
        return selectionStartPoint != null;
    }

    private int getSelectionStartX() {
        int startX = (selectionStartPoint.x < mousePosition.x)
                ? selectionStartPoint.x
                : mousePosition.x;
        return startX;
    }

    private int getSelectionStartY() {
        int startY = (selectionStartPoint.y < mousePosition.y)
                ? selectionStartPoint.y
                : mousePosition.y;
        return startY;
    }

    private int getRectangleSelectionWidth() {
        int width = Math.abs(mousePosition.x - selectionStartPoint.x);
        return width;
    }

    private int getRectangleSelectionHeight() {
        int height = Math.abs(mousePosition.y - selectionStartPoint.y);
        return height;
    }

    private boolean isNodeInsideRectangle(int startX, int startY, int width, int height, NodeView nodeView) {
        int x = nodeView.getUpperLeftCorner().x;
        int y = nodeView.getUpperLeftCorner().y;
        return x > startX && y > startY && x < startX + width && y < startY + height
                || x + nodeView.getWidth() > startX && y > startY && x + nodeView.getWidth() < startX + width && y < startY + height
                || x > startX && y + nodeView.getHeight() > startY && x < startX + width && y + nodeView.getHeight() < startY + height
                || x + nodeView.getWidth() > startX && y + nodeView.getHeight() > startY && x + nodeView.getWidth() < startX + width
                && y + nodeView.getHeight() < startY + height;
    }

    private void clearSelected() {
        graphPanel.getSelectedNodeViews().clear();
    }

    private boolean isNodeSelected(NodeView nodeViewAtPoint) {
        return !(nodeViewAtPoint != null && !graphPanel.getSelectedNodeViews().contains(nodeViewAtPoint));
    }

    private void addToSelected(NodeView nodeViewAtPoint) {
        graphPanel.getSelectedNodeViews().add(nodeViewAtPoint);
    }

    private boolean isNothingSelected(NodeView nodeViewAtPoint, int x, int y) {
        return nodeViewAtPoint == null && graphPanel.getGraphView().getLinkViewAtPoint(x, y) == null;
    }

    private void addNodeViewsToRectangle() {
        int startSelectionX = getSelectionStartX();
        int startSelectionY = getSelectionStartY();
        int rectangleWidth = getRectangleSelectionWidth();
        int rectangleHeight = getRectangleSelectionHeight();
        clearSelected();
        for (NodeView nodeView : graphPanel.getGraphView().getNodeViews()) {
            if (isNodeInsideRectangle(startSelectionX, startSelectionY, rectangleWidth, rectangleHeight, nodeView)) {
                addToSelected(nodeView);
            }
        }
    }

    private void paintRectangleSelection(Graphics2D g2) {
        paintRectangleSelectionBorder(g2);
        paintRectangleSelectionInside(g2);
    }

    private void paintRectangleSelectionBorder(Graphics2D g2) {
        int startX = getSelectionStartX();
        int startY = getSelectionStartY();
        g2.setColor(COLORS.BLUE);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRect(startX, startY,
                Math.abs(mousePosition.x - selectionStartPoint.x),
                Math.abs(mousePosition.y - selectionStartPoint.y));
    }

    private void paintRectangleSelectionInside(Graphics2D g2) {
        int startX = getSelectionStartX();
        int startY = getSelectionStartY();
        g2.setColor(COLORS.FILL_BLUE);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 6 * 0.1f));
        g2.fillRect(startX, startY,
                Math.abs(mousePosition.x - selectionStartPoint.x),
                Math.abs(mousePosition.y - selectionStartPoint.y));
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
        g2.drawOval(selectedNodeView.getUpperLeftCorner().x,
                selectedNodeView.getUpperLeftCorner().y,
                selectedNodeView.getWidth(),
                selectedNodeView.getHeight());
    }

    private void paintSelectedNodeInside(Graphics2D g2, NodeView selectedNodeView) {
        g2.setColor(COLORS.FILL_BLUE);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 6 * 0.1f));
        g2.fillOval(selectedNodeView.getUpperLeftCorner().x,
                selectedNodeView.getUpperLeftCorner().y,
                selectedNodeView.getWidth(),
                selectedNodeView.getHeight());
    }
}
