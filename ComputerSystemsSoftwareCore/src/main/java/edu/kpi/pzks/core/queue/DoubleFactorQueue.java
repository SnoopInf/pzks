package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.queue.factors.FactorEvaluator;
import edu.kpi.pzks.core.queue.utils.DoubleFactorNodeComparator;
import edu.kpi.pzks.core.queue.utils.QueuedNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author smarx
 */
public class DoubleFactorQueue implements Queue {
    private Collection<Node> nodes;
    private final FactorEvaluator factorEvaluatorPrimary;
    private final FactorEvaluator factorEvaluatorSecondary;

    public DoubleFactorQueue(FactorEvaluator factorEvaluator1, FactorEvaluator factorEvaluator2, Collection<Node> nodes) {
        this.factorEvaluatorPrimary = factorEvaluator1;
        this.factorEvaluatorSecondary = factorEvaluator2;
        this.nodes = nodes;
    }

    @Override
    public Collection<QueuedNode> evaluate() {
        DoubleFactorNodeComparator comparator = new DoubleFactorNodeComparator(factorEvaluatorPrimary, factorEvaluatorSecondary);

        List<Node> nodeList = new ArrayList<>(nodes);
        Collections.sort(nodeList, comparator);

        List<QueuedNode> queuedNodes = new ArrayList<>();
        for (Node n : nodeList) {
            QueuedNode queuedNode = new QueuedNode(n, factorEvaluatorPrimary.evaluateFactorForNode(n)); // костыль?
            queuedNodes.add(queuedNode);
        }

        return queuedNodes;
    }

}
