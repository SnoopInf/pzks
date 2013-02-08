package edu.kpi.pzks.gui.modelview.impl;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.gui.modelview.LinkView;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPopupMenu;

/**
 *
 * @author asmirnova
 */
public class LinkViewImpl implements LinkView {

    protected Point bendPoint;
    protected boolean selected;
    protected Link link;

    public LinkViewImpl(Link link) {
        this.link = link;

    }

    public Link getLink() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBendPoint(int x, int y) {
        this.bendPoint = new Point(x, y);
    }

    public Point getBendPoint() {
        return bendPoint;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        throw new UnsupportedOperationException("Not supported yet.");
    }
//    @Override
//    public int getWidth() {
//        return (int) Math.abs(getX2() - getX1());
//    }
//    @Override
//    public int getHeight() {
//        return (int) Math.abs(getY2() - getY1());
//    }
//    public double getX1() {
//        return node1.getX() + node1.getWidth() / 2;
//    }
//    public double getY1() {
//        return node1.getY() + node1.getHeight() / 2;
//    }
//    public Point2D getP1() {
//        return new Point2D.Double(node1.getX() + node1.getWidth() / 2, node1.getY() + node1.getHeight() / 2);
//    }
//    public double getX2() {
//        return node2.getX() + node2.getWidth() / 2;
//    }
//    public double getY2() {
//        return node2.getY() + node2.getHeight() / 2;
//    }
//    public Point2D getP2() {
//        return new Point2D.Double(node2.getX() + node2.getWidth() / 2, node2.getY() + node2.getHeight() / 2);
//    }
//    @Override
//    public int compareTo(Object o) {
//        return super.weight.compareTo(((Link) o).getNumber());
//    }
//    public Line2D.Double[] getLines() {
//        return arrow.getLines();
//    }
//
//    @Override
//    public boolean contains(int x, int y) {
//        return arrow.contains(x, y);
//    }
//
//    @Override
//    public String getName() {
//        return "Link";
//    }

    @Override
    public JPopupMenu getPopupMenu() {
        throw new UnsupportedOperationException("Not supported yet.");
//        return new LinkPopup(this);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return this.selected;
    }
}
