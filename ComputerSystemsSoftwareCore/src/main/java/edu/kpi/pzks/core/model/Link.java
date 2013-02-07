package edu.kpi.pzks.core.model;

import java.util.Objects;

/**
 *
 * @author Aloren
 */
public class Link implements GraphObject {

    private static final long serialVersionUID = 3;
    private Node fromNode;
    private Node toNode;
    //TODO please move me to gui
//    private Arrow arrow;

    public Link() {
    }

    public Link(Node fromNode, Node toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        //TODO please move me to gui
//        arrow = new Arrow(this);
    }
//TODO please move me to gui
//    @Override
//    public void setSelected(boolean selected) {
//        this.selected = selected;
////        arrow.setSelected(selected);
//    }
//    @Override
//    public void paintComponent(Graphics2D g2) {
//        arrow.paintComponent(g2);
//    }

    public Node getFromNode() {
        return fromNode;
    }

    public void setFromNode(Node fromNode) {
        this.fromNode = fromNode;
//        arrow.pn1 = node1;
    }

    public Node getToNode() {
        return toNode;
    }

    public void setToNode(Node toNode) {
        this.toNode = toNode;
        //TODO please move me to gui
//        arrow.pn2 = node2;
    }
//TODO please move me to gui
//    public void setX(int x) {
//        arrow.uPoint.x = x;
//    }
//    public void setY(int y) {
//        arrow.uPoint.y = y;
//    }
//    @Override
//    public int getX() {
//        return (int) arrow.uPoint.x;
//    }
//    @Override
//    public int getY() {
//        return (int) arrow.uPoint.y;
//    }
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
//    public void setPoint(int x, int y) {
//        arrow.uPoint = new Point2D.Double(x, y);
//    }
//
//    @Override
//    public void setPoint(Point2D.Double p) {
//        arrow.uPoint = p;
//    }
//
//    @Override
//    public Point2D.Double getPoint() {
//        return arrow.uPoint;
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
//
//    @Override
//    public JPopupMenu getPopup(Graph graph) {
//        return new LinkPopup(this, graph.getLinks());
//    }

    public void removeFromOutputNodes() {
        fromNode.getOutputNodes().remove(toNode);
    }

    public void removeFromInputNodes() {
        toNode.getInputNodes().remove(fromNode);
    }

    public void addToOutputNodes() {
        fromNode.getOutputNodes().add(toNode);
    }

    public void addToInputNodes() {
        toNode.getInputNodes().add(fromNode);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.fromNode);
        hash = 79 * hash + Objects.hashCode(this.toNode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Link other = (Link) obj;
        if (!Objects.equals(this.fromNode, other.fromNode)) {
            return false;
        }
        if (!Objects.equals(this.toNode, other.toNode)) {
            return false;
        }
        return true;
    }
}
