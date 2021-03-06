package edu.kpi.pzks.gui.modelview.impl;

import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.ui.popups.NodeViewPopup;
import edu.kpi.pzks.gui.utils.COLORS;
import edu.kpi.pzks.gui.utils.CONSTANTS;
import edu.kpi.pzks.gui.utils.PaintUtils;
import java.awt.BasicStroke;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import javax.swing.JPopupMenu;

/**
 * @author aloren
 */
public class NodeViewImpl implements NodeView {

    protected RectangularShape shape;
    protected Node node;
    protected boolean isSelected;

    public NodeViewImpl(Node node, Point2D.Double point) {
        this(node, point.x, point.y);
    }

    public NodeViewImpl(Node node) {
        this(node, 0, 0);
    }

    public NodeViewImpl(Node node, double x, double y) {
        this.node = node;
        this.shape = new Ellipse2D.Double(x, y,
                CONSTANTS.NODE_WIDTH, CONSTANTS.NODE_HEIGHT);
    }

    @Override
    public JPopupMenu getPopupMenu() {
        return new NodeViewPopup(this);
    }

    @Override
    public Point getUpperLeftCorner() {
        return new Point((int) shape.getX(), (int) shape.getY());
    }

    @Override
    public void setUpperLeftCorner(Point point) {
        shape.setFrame(point.getX(), point.getY(), CONSTANTS.NODE_WIDTH, CONSTANTS.NODE_HEIGHT);
    }

    @Override
    public int getWidth() {
        return (int) this.shape.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) this.shape.getHeight();
    }

    @Override
    public Point getCenter() {
        return new Point((int) shape.getCenterX(), (int) shape.getCenterY());
    }

    @Override
    public String getName() {
        return "Node w=" + node.getWeight();
    }

    @Override
    public Node getNode() {
        return this.node;
    }

    @Override
    public void paint(Graphics2D g2) {
        paintNodeEllipse(g2);
        paintNodeEllipseBorder(g2);
        paintWeightString(g2);
        paintIdString(g2);
    }

    @Override
    public boolean contains(int x, int y) {
        return this.shape.contains(x, y);
    }

    @Override
    public NodeViewPopup getPopup() {
        return new NodeViewPopup(this);
    }

    @Override
    public void setWeight(int weight) {
        this.node.setWeight(weight);
    }

    @Override
    public int getWeight() {
        return this.node.getWeight();
    }

    protected void paintWeightString(Graphics2D g2) {
        g2.setFont(PaintUtils.getFont());
        FontMetrics metrics = g2.getFontMetrics(g2.getFont());
        String weightString = Integer.toString(node.getWeight());
        int x = (int) (shape.getX() + shape.getWidth() / 2 - metrics.stringWidth(weightString) / 2);
        int y = (int) (shape.getY() + shape.getHeight() / 2 + metrics.getHeight() / 3);
        g2.drawString(weightString, x, y);
    }

    private void paintIdString(Graphics2D g2) {
        g2.setFont(PaintUtils.getIdFont());
        FontMetrics metrics = g2.getFontMetrics(g2.getFont());
        String idString = "" + node.getId();
        int x = (int) (shape.getX() - metrics.stringWidth(idString) / 4);
        int y = (int) (shape.getY() - metrics.getHeight() / 4);
        g2.drawString(idString, x, y);
    }

    protected void paintNodeEllipseBorder(Graphics2D g2) {
        if (isSelected) {
            g2.setStroke(new BasicStroke(3));
            g2.setColor(COLORS.NODE_BORDER_SELECTED_COLOR);
        } else {
            g2.setStroke(new BasicStroke(1.5f));
            g2.setColor(COLORS.NODE_BORDER_COLOR);
        }
        g2.draw(shape);
    }

    protected void paintNodeEllipse(Graphics2D g2) {
        if (isSelected) {
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 6 * 0.1f));
            g2.setColor(COLORS.NODE_SELECTED_COLOR);
        } else {
            g2.setColor(COLORS.NODE_COLOR);
        }
        g2.fill(shape);
//        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    @Override
    public Point getTopLeftForCentreAt(int x, int y) {
        return new Point(x - getWidth() / 2, y - getHeight() / 2);
    }
}
