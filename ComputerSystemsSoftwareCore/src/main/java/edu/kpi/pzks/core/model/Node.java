package edu.kpi.pzks.core.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Aloren
 */
public class Node implements GraphObject {

    private static final long serialVersionUID = 4;
    protected int weight;
    protected Set<Node> inputNodes = new HashSet<>();
    protected Set<Node> outputNodes = new HashSet<>();

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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
