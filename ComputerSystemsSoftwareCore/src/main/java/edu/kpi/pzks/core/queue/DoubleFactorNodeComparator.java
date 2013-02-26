package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Node;

import java.util.Comparator;

/**
* @author smarx
*/
class DoubleFactorNodeComparator implements Comparator<Node> {

    private boolean naturalOrder;
    private final NodeComparator nodeComparatorPrimary;
    private final NodeComparator nodeComparatorSecondary;

    DoubleFactorNodeComparator(FactorEvaluator factorEvaluator1, FactorEvaluator factorEvaluator2) {
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
