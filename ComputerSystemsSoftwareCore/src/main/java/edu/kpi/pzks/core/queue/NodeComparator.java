package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Node;

import java.util.Comparator;

/**
* @author smarx
*/
public class NodeComparator implements Comparator<Node> {

    private FactorEvaluator factorEvaluator;

    NodeComparator(FactorEvaluator factorEvaluator) {
        this.factorEvaluator = factorEvaluator;
    }

    @Override
    public int compare(Node n1, Node n2) {
        if(factorEvaluator.hasNaturalOrder())
            return Double.compare(factorEvaluator.evaluateFactorForNode(n1), factorEvaluator.evaluateFactorForNode(n2));
        return Double.compare(factorEvaluator.evaluateFactorForNode(n2), factorEvaluator.evaluateFactorForNode(n1));
    }
}
