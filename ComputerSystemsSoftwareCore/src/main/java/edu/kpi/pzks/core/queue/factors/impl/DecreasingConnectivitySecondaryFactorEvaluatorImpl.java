package edu.kpi.pzks.core.queue.factors.impl;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.queue.factors.AbstractFactorEvaluator;

import java.util.Collection;

/**
 * Task #4; part2
 *
 * @author smarx
 */
public class DecreasingConnectivitySecondaryFactorEvaluatorImpl extends AbstractFactorEvaluator {
    public DecreasingConnectivitySecondaryFactorEvaluatorImpl(Collection<Node> nodes, Collection<Link> links) {
        super(nodes, links);
    }

    @Override
    public double evaluateFactorForNode(Node node) {
        return node.getInputNodes().size() + node.getOutputNodes().size();
    }

    @Override
    public boolean hasNaturalOrder() {
        return false;
    }
}
