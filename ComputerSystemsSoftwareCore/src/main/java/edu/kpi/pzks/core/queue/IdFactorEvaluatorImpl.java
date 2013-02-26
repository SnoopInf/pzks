package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Node;

/**
 * @author smarx
 */
public class IdFactorEvaluatorImpl implements FactorEvaluator {
    @Override
    public double evaluateFactorForNode(Node node) {
        return node.getId();
    }

    @Override
    public boolean hasNaturalOrder() {
        return true;
    }
}
