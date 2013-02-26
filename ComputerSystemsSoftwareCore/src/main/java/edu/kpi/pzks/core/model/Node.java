package edu.kpi.pzks.core.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Aloren
 */
public class Node extends GraphObject {
    private static final long serialVersionUID = 4;
    protected Set<Node> inputNodes = new HashSet<>();
    protected Set<Node> outputNodes = new HashSet<>();

    private int id;

    public Node() {
        super();
    }

    public Node(int weight, int id) {
        this(weight);
        this.setId(id);
    }

    public Node(int weight) {
        super(weight);
    }

    public Set<Node> getInputNodes() {
        return inputNodes;
    }

    public Set<Node> getOutputNodes() {
        return outputNodes;
    }

    public void addInputNode(Node node) {
        if (node != null) {
            inputNodes.add(node);
        }
    }

    public void addOutputNode(Node node) {
        if (node != null) {
            outputNodes.add(node);
        }
    }

    @Override
    public String toString() {
        return "Node " + getName() + " w: " + getWeight();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
