package edu.kpi.pzks.core.queue.factors.impl;

import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.queue.factors.FactorEvaluator;

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
