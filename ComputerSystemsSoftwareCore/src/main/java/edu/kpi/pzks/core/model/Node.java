package edu.kpi.pzks.core.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Aloren
 */
public class Node implements GraphObject {
    
    private static final long serialVersionUID = 4;
    //TODO please move me to gui
//    protected Ellipse2D.Double ellipse;
    protected List<Node> inputNodes = new LinkedList<>();
    protected List<Node> outputNodes = new LinkedList<>();
//TODO please move me to gui
//    public Node(int number, Point2D.Double point) {
//        this(number, (int) point.x, (int) point.y);
//    }

//    public Node(int number, int x, int y) {
//        this.weight = number;
//        this.ellipse = new Ellipse2D.Double(x, y, CONSTANTS.PLACE_WIDTH, CONSTANTS.PLACE_WIDTH);
//    }

//    @Override
//    public void paintComponent(Graphics2D g2) {
//        g2.fill(getEllipse());
//        if (isSelected()) {
//            g2.setStroke(new BasicStroke(3));
//            g2.setColor(CONSTANTS.BORDER_SELECTED_COLOR);
//        } else {
//            g2.setColor(Color.BLACK);
//        }
//        g2.draw(getEllipse());
//        int x = (int) getEllipse().x;
//        int y = (int) getEllipse().y;
//        g2.drawString(getName(), x - 10, y - 5);
//        g2.setStroke(new BasicStroke(1));
//    }
//
//    @Override
//    public int compareTo(Object o) {
//        Node otherPlace = (Node) o;
//        return weight.compareTo(otherPlace.getNumber());
//    }
//
//    @Override
//    public void setPoint(int x, int y) {
//        this.ellipse.x = x;
//        this.ellipse.y = y;
//    }
//
//    @Override
//    public void setPoint(Point2D.Double p) {
//        this.ellipse.x = p.x;
//        this.ellipse.y = p.y;
//    }
//
//    @Override
//    public Point2D.Double getPoint() {
//        return new Point2D.Double(this.getEllipse().x, this.getEllipse().y);
//    }
//
//    @Override
//    public int getX() {
//        return (int) this.getEllipse().x;
//    }
//
//    @Override
//    public int getY() {
//        return (int) this.getEllipse().y;
//    }
//
//    @Override
//    public int getWidth() {
//        return (int) this.getEllipse().width;
//    }
//
//    @Override
//    public int getHeight() {
//        return (int) this.getEllipse().height;
//    }
//
//    @Override
//    public boolean contains(int x, int y) {
//        return getEllipse().contains(x, y);
//    }
//
//    public Rectangle getBounds() {
//        return getEllipse().getBounds();
//    }
//
//    public Ellipse2D.Double getEllipse() {
//        return ellipse;
//    }

//    @Override
//    public String getName() {
//        return "Node"+getNumber();
//    }

//    public JPopupMenu getPopup(Graph graph) {
//        return new NodePopup(this);
//    }

    public List<Node> getInputNodes() {
        return inputNodes;
    }

    public List<Node> getOutputNodes() {
        return outputNodes;
    }
    
    public void addInputNode(Node node) {
        inputNodes.add(node);
    }
    
    public void addOutputNode(Node node) {
        outputNodes.add(node);
    }
}
