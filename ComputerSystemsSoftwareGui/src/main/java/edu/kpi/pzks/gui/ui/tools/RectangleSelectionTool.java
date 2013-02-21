package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.gui.modelview.LinkView;
import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.ui.GraphPanel;
import edu.kpi.pzks.gui.utils.COLORS;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

/**
 *
 * @author aloren
 */
public class RectangleSelectionTool implements SelectionDraggingTool {

    private final GraphPanel graphPanel;
    private Point selectionStartPoint;
    private Point mousePosition = new Point(0, 0);

    public RectangleSelectionTool(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public void paint(Graphics2D g2) {
        if (isRectangleSelect()) {
            paintRectangleSelection(g2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        if (isNothingSelected(x, y)) {
            beginRectangleSelection(x, y);
        }
        graphPanel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        finishRectangleSelect();
        graphPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        snapToGrid(e);
        if (isRectangleSelect()) {
            clearSelected();
            addNodeViewsToRectangle();
            addLinkViewsToRectabgle();
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

    private void paintRectangleSelection(Graphics2D g2) {
        paintRectangleSelectionBorder(g2);
        paintRectangleSelectionInside(g2);
    }

    private void paintRectangleSelectionBorder(Graphics2D g2) {
        int startX = getSelectionStartX();
        int startY = getSelectionStartY();
        g2.setColor(COLORS.GRID_COLOR);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRect(startX, startY,
                Math.abs(mousePosition.x - selectionStartPoint.x),
                Math.abs(mousePosition.y - selectionStartPoint.y));
    }

    private void paintRectangleSelectionInside(Graphics2D g2) {
        int startX = getSelectionStartX();
        int startY = getSelectionStartY();
        g2.setColor(COLORS.GRID_FILL_COLOR);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 6 * 0.1f));
        g2.fillRect(startX, startY,
                Math.abs(mousePosition.x - selectionStartPoint.x),
                Math.abs(mousePosition.y - selectionStartPoint.y));
    }

    private boolean isNothingSelected(int x, int y) {
        return graphPanel.getGraphView().getNodeViewAtPoint(x, y) == null
                && graphPanel.getGraphView().getLinkViewAtPoint(x, y) == null;
    }

    private void addNodeViewsToRectangle() {
        int startSelectionX = getSelectionStartX();
        int startSelectionY = getSelectionStartY();
        int rectangleWidth = getRectangleSelectionWidth();
        int rectangleHeight = getRectangleSelectionHeight();
        for (NodeView nodeView : graphPanel.getGraphView().getNodeViews()) {
            if (isNodeInsideRectangle(startSelectionX, startSelectionY, rectangleWidth, rectangleHeight, nodeView)) {
                addToSelected(nodeView);
            }
        }
    }

    private void addLinkViewsToRectabgle() {
        int startSelectionX = getSelectionStartX();
        int startSelectionY = getSelectionStartY();
        int rectangleWidth = getRectangleSelectionWidth();
        int rectangleHeight = getRectangleSelectionHeight();
        for (LinkView linkView : graphPanel.getGraphView().getLinkViews()) {
            if (isLinkInsideRectangle(startSelectionX, startSelectionY, rectangleWidth, rectangleHeight, linkView)) {
                addToSelected(linkView);
            }
        }
    }

    private void addToSelected(NodeView nodeViewAtPoint) {
        graphPanel.addSelectedNodeView(nodeViewAtPoint);
    }

    private void addToSelected(LinkView linkViewAtPoint) {
        graphPanel.addSelectedLinkView(linkViewAtPoint);
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

    private boolean isLinkInsideRectangle(int startX, int startY, int width, int height, LinkView linkView) {
        Rectangle rect = new Rectangle(startX, startY, width, height);
        boolean isLinesInside = true;
        for (Line2D.Double line : linkView.getLines()) {
            final boolean containsLine = rect.contains(line.getP1()) && rect.contains(line.getP2());
            isLinesInside = isLinesInside & containsLine;
        }
        return isLinesInside;
    }

    private void clearSelected() {
        graphPanel.clearSelectedLinkViews();
        graphPanel.clearSelectedNodeViews();
    }
}
