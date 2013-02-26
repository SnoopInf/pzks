package edu.kpi.pzks.core.queue.factors;

import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.queue.AbstractFactorEvaluator;

import java.util.Collection;

/**
 * Метод формирования очередей №1
 *
 * @author smarx
 */
public class WeightedCriticalFactorEvaluatorImpl extends AbstractFactorEvaluator {
    public WeightedCriticalFactorEvaluatorImpl(Collection<Node> nodes) {
        super(nodes);
    }

    @Override
    public double evaluateFactorForNode(Node node) {
        return getWeightedCriticalPathFromTop(node) + getWeightedCriticalNumberFromTop(node); // пронормированная сумма
    }

    @Override
    public boolean hasNaturalOrder() {
        return false; // в порядке убывания
    }
}
