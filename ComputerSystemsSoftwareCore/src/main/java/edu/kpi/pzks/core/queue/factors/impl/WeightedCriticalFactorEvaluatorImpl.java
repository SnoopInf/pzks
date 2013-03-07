package edu.kpi.pzks.core.queue.factors.impl;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.queue.factors.AbstractFactorEvaluator;

import java.util.Collection;

/**
 * Метод формирования очередей №1
 *
 * @author smarx
 */
public class WeightedCriticalFactorEvaluatorImpl extends AbstractFactorEvaluator {
    public WeightedCriticalFactorEvaluatorImpl(Collection<Node> nodes, Collection<Link> links) {
        super(nodes, links);
    }

    @Override
    public double evaluateFactorForNode(Node node) {
        return getWeightedCriticalPathFromTopTo(node) + getWeightedCriticalNumberFromTopTo(node); // пронормированная сумма
    }

    @Override
    public boolean hasNaturalOrder() {
        return false; // в порядке убывания
    }
}
