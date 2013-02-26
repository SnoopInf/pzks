package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Node;

import java.util.Collection;

/**
 * Task #4; part2
 *
 * @author smarx
 */
public class DecreasingConnectivitySecondaryFactorEvaluatorImpl extends AbstractFactorEvaluator {
    public DecreasingConnectivitySecondaryFactorEvaluatorImpl(Collection<Node> nodes) {
        super(nodes);
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
