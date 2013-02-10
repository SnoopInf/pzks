package edu.kpi.pzks.gui.modelview.impl;

import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.utils.COLORS;
import edu.kpi.pzks.gui.utils.CONSTANTS;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import javax.swing.JPopupMenu;

/**
 *
 * @author asmirnova
 */
public class NodeViewImpl implements NodeView {

    protected Ellipse2D.Double ellipse;
    protected boolean selected;
    private final Node node;

    public NodeViewImpl(Node node, Point2D.Double point) {
        this(node, point.x, point.y);
    }

    public NodeViewImpl(Node node) {
        this(node, 0, 0);
    }

    public NodeViewImpl(Node node, double x, double y) {
        this.node = node;
        this.ellipse = new Ellipse2D.Double(x, y,
                CONSTANTS.NODE_WIDTH, CONSTANTS.NODE_HEIGHT);
    }

    @Override
    public JPopupMenu getPopupMenu() {
        throw new UnsupportedOperationException("Not supported yet.");
//        return new NodePopup(this);
    }

    @Override
    public Point getUpperLeftCorner() {
        return new Point((int) ellipse.getX(), (int) ellipse.getY());
    }

    @Override
    public void setUpperLeftCorner(Point point) {
        ellipse.x = point.getX();
        ellipse.y = point.getY();
    }

    @Override
    public int getWidth() {
        return (int) this.ellipse.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) this.ellipse.getHeight();
    }

    @Override
    public Point getCenter() {
        return new Point((int) ellipse.getCenterX(), (int) ellipse.getCenterY());
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
        g2.setColor(COLORS.NODE_COLOR);
        g2.fill(ellipse);
        g2.setColor(COLORS.NODE_BORDER_COLOR);
        g2.setStroke(new BasicStroke(1.5f));
        g2.draw(ellipse);
        //TODO add string length to position weight right
        int x = (int) (ellipse.x + ellipse.width / 2);
        int y = (int) (ellipse.y + ellipse.height / 2);
        g2.drawString(Integer.toString(node.getWeight()), x, y);
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean isSelected() {
        return this.selected;
    }

    @Override
    public boolean contains(int x, int y) {
        return this.ellipse.contains(x, y);
    }
}
