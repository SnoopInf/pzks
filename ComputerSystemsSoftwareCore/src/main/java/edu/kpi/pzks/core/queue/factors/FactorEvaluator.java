package edu.kpi.pzks.core.queue.factors;

import edu.kpi.pzks.core.model.Node;

/**
 * @author smarx
 */
public interface FactorEvaluator {
    double evaluateFactorForNode(Node node);

    boolean hasNaturalOrder();
}
