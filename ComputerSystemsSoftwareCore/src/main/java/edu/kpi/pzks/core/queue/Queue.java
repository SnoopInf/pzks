package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Node;

import java.util.*;

/**
 * @author smarx
 */
public class Queue {
    private FactorEvaluator factorEvaluator;
    private Collection<Node> nodes;

    public Queue(FactorEvaluator factorEvaluator, Collection<Node> nodes) {
        this.factorEvaluator = factorEvaluator;
        this.nodes = nodes;
    }


    public Collection<QueuedNode> evaluate() {
        NodeComparator comparator = new NodeComparator(factorEvaluator.hasNaturalOrder());

        List<QueuedNode> queuedNodes = new ArrayList<>();
        for(Node n : nodes) {
            QueuedNode queuedNode = new QueuedNode(n, factorEvaluator.evaluateFactorForNode(n));
            queuedNodes.add(queuedNode);
        }

        Collections.sort(queuedNodes, comparator);
        return queuedNodes;
    }

    private class NodeComparator implements Comparator<QueuedNode> {

        private boolean naturalOrder;

        private NodeComparator(boolean order) {
            naturalOrder = order;
        }

        @Override
        public int compare(QueuedNode n1, QueuedNode n2) {
            if(naturalOrder)
                return Double.compare(n1.getQueueingFactor(), n2.getQueueingFactor());
            return Double.compare(n2.getQueueingFactor(), n1.getQueueingFactor());
        }
    }
}
