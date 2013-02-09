package edu.kpi.pzks.core.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Aloren
 */
public class Node extends GraphObject {

    private static final long serialVersionUID = 4;
    protected Set<Node> inputNodes = new HashSet<>();
    protected Set<Node> outputNodes = new HashSet<>();

    public Node() {
        this(0);
    }

    public Node(int weigth) {
        super(weigth);
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
}
