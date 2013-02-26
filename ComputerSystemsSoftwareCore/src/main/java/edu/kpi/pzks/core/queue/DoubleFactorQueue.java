package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Node;

import java.util.*;

/**
 * @author smarx
 */
public class DoubleFactorQueue {
    private Collection<Node> nodes;
    private final FactorEvaluator factorEvaluatorPrimary;
    private final FactorEvaluator factorEvaluatorSecondary;

    public DoubleFactorQueue(FactorEvaluator factorEvaluator1, FactorEvaluator factorEvaluator2, Collection<Node> nodes) {
        this.factorEvaluatorPrimary = factorEvaluator1;
        this.factorEvaluatorSecondary = factorEvaluator2;
        this.nodes = nodes;
    }


    public Collection<QueuedNode> evaluate() {
        DoubleFactorNodeComparator comparator = new DoubleFactorNodeComparator(factorEvaluatorPrimary, factorEvaluatorSecondary);

        List<Node> nodeList = new ArrayList<>(nodes);
        Collections.sort(nodeList, comparator);

        List<QueuedNode> queuedNodes = new ArrayList<>();
        for(Node n : nodeList) {
            QueuedNode queuedNode = new QueuedNode(n, factorEvaluatorPrimary.evaluateFactorForNode(n)); // костыль?
            queuedNodes.add(queuedNode);
        }

        return queuedNodes;
    }

    private class DoubleFactorNodeComparator implements Comparator<Node> {

        private boolean naturalOrder;
        private final NodeComparator nodeComparatorPrimary;
        private final NodeComparator nodeComparatorSecondary;

        private DoubleFactorNodeComparator(FactorEvaluator factorEvaluator1, FactorEvaluator factorEvaluator2) {
            this.nodeComparatorPrimary = new NodeComparator(factorEvaluator1);
            this.nodeComparatorSecondary = new NodeComparator(factorEvaluator2);
        }

        @Override
        public int compare(Node n1, Node n2) {
            int primaryComparison = nodeComparatorPrimary.compare(n1, n2);
            if(primaryComparison != 0)
                return primaryComparison;
            return nodeComparatorSecondary.compare(n1, n2);
        }
    }
}
