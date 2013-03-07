package edu.kpi.pzks.core.queue.factors.impl;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.queue.factors.AbstractFactorEvaluator;

import java.util.Collection;

/**
 * Task #4; part1
 *
 * @author smarx
 */
public class DecreasingCriticalNumberPrimaryFactorEvaluatorImpl extends AbstractFactorEvaluator {
    public DecreasingCriticalNumberPrimaryFactorEvaluatorImpl(Collection<Node> nodes, Collection<Link> links) {
        super(nodes, links);
    }

    @Override
    public double evaluateFactorForNode(Node node) {
        return getCriticalNumberFromTopTo(node);
    }

    @Override
    public boolean hasNaturalOrder() {
        return false;
    }
}
