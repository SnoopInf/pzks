package edu.kpi.pzks.gui.modelview.impl;

import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.utils.CONSTANTS;
import java.awt.BasicStroke;
import java.awt.Graphics;
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

    public NodeViewImpl(Node node, double x, double y) {
        this.node = node;
        this.ellipse = new Ellipse2D.Double(x, y,
                CONSTANTS.NODE_WIDTH, CONSTANTS.NODE_HEIGHT);
    }

    public JPopupMenu getPopupMenu() {
        throw new UnsupportedOperationException("Not supported yet.");
//        return new NodePopup(this);
    }

    public Point getUpperLeftCorner() {
        return new Point((int) ellipse.getX(), (int) ellipse.getY());
    }

    public void setUpperLeftCorner(Point point) {
        ellipse.x = point.getX();
        ellipse.y = point.getY();
    }

    public int getWidth() {
        return (int) this.ellipse.getWidth();
    }

    public int getHeight() {
        return (int) this.ellipse.getHeight();
    }

    public String getName() {
//        return "Node"+getNumber();
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Node getNode() {
        return this.node;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.fill(ellipse);
        if (isSelected()) {
            g2.setStroke(new BasicStroke(3));
            g2.setColor(CONSTANTS.BORDER_SELECTED_COLOR);
        } else {
            g2.setColor(CONSTANTS.BORDER_COLOR);
        }
        g2.draw(ellipse);
        g2.drawString(getName(), (int) ellipse.x - 10, (int) ellipse.y - 5);
        g2.setStroke(new BasicStroke(1));
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return this.selected;
    }
}
