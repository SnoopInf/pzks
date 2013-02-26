package edu.kpi.pzks.core.queue.factors;

import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.queue.FactorEvaluator;

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
