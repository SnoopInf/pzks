package edu.kpi.pzks.gui.modelview.impl;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.gui.modelview.LinkView;
import edu.kpi.pzks.gui.modelview.NodeView;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPopupMenu;

/**
 *
 * @author asmirnova
 */
public class LinkViewImpl implements LinkView {
    
    protected NodeView fromNodeView;
    protected NodeView toNodeView;
    protected Point bendPoint;
    protected boolean selected;
    protected Link link;

    public LinkViewImpl(NodeView fromNodeView, NodeView toNodeView) {
        Node fromNode = fromNodeView.getNode();
        Node toNode = toNodeView.getNode();
        this.link = new Link(fromNode, toNode);
        this.fromNodeView = fromNodeView;
        this.toNodeView = toNodeView;

    }

    public Link getLink() {
        return this.link;
    }

    public void setBendPoint(int x, int y) {
        this.bendPoint = new Point(x, y);
    }

    public Point getBendPoint() {
        return bendPoint;
    }

    public NodeView getFromNodeView() {
        return fromNodeView;
    }

    public void setFromNodeView(NodeView fromNodeView) {
        this.fromNodeView = fromNodeView;
    }

    public NodeView getToNodeView() {
        return toNodeView;
    }

    public void setToNodeView(NodeView toNodeView) {
        this.toNodeView = toNodeView;
    }
    
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        throw new UnsupportedOperationException("Not supported yet.");
    }
//    public int getWidth() {
//        return (int) Math.abs(getX2() - getX1());
//    }
//    public int getHeight() {
//        return (int) Math.abs(getY2() - getY1());
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

    public boolean contains(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
