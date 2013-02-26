package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Node;

import java.util.Collection;

/**
 * Task #4; part1
 *
 * @author smarx
 */
public class DecreasingCriticalNumberPrimaryFactorEvaluatorImpl extends AbstractFactorEvaluator {
    public DecreasingCriticalNumberPrimaryFactorEvaluatorImpl(Collection<Node> nodes) {
        super(nodes);
    }

    @Override
    public double evaluateFactorForNode(Node node) {
        return getCriticalNumberFromTop(node);
    }

    @Override
    public boolean hasNaturalOrder() {
        return false;
    }
}
