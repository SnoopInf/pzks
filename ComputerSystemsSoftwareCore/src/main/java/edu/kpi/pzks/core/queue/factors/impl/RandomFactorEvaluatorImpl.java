package edu.kpi.pzks.core.queue.factors.impl;

import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.queue.factors.FactorEvaluator;

import java.util.Random;

/**
 * @author smarx
 */
public class RandomFactorEvaluatorImpl implements FactorEvaluator {
    Random random = new Random();

    @Override
    public double evaluateFactorForNode(Node node) {
        return random.nextDouble();
    }

    @Override
    public boolean hasNaturalOrder() {
        return true;
    }
}
