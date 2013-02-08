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

    public Link() {
    }

    public Link(Node fromNode, Node toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
    }

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
