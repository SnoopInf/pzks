package edu.kpi.pzks.core.queue.utils;

import edu.kpi.pzks.core.model.Node;

/**
 * @author smarx
 */
public class QueuedNode {
    private Node node;
    private double queueingFactor;

    public QueuedNode(Node node, double queueingFactor) {
        this.node = node;
        this.queueingFactor = queueingFactor;
    }

    public Node getNode() {
        return node;
    }

    public double getQueueingFactor() {
        return queueingFactor;
    }

    @Override
    public String toString() {
        return String.format("%d (%.4f)", node.getId(), queueingFactor);
    }
}
