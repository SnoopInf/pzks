package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Node;

/**
 * @author smarx
 */
public class StaticFactorEvaluatorImpl implements FactorEvaluator {
    @Override
    public double evaluateFactorForNode(Node node) {
        return 1;
    }

    @Override
    public boolean hasNaturalOrder() {
        return true;
    }
}
