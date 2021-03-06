package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.queue.factors.FactorEvaluator;
import edu.kpi.pzks.core.queue.utils.NodeComparator;
import edu.kpi.pzks.core.queue.utils.QueuedNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author smarx
 */
public class SingleFactorQueue implements Queue {
    private FactorEvaluator factorEvaluator;
    private Collection<Node> nodes;

    public SingleFactorQueue(FactorEvaluator factorEvaluator, Collection<Node> nodes) {
        this.factorEvaluator = factorEvaluator;
        this.nodes = nodes;
    }

    @Override
    public Collection<QueuedNode> evaluate() {
        NodeComparator comparator = new NodeComparator(factorEvaluator);

        List<Node> nodeList = new ArrayList<>(nodes);
        Collections.sort(nodeList, comparator);

        List<QueuedNode> queuedNodes = new ArrayList<>();
        for (Node n : nodeList) {
            queuedNodes.add(new QueuedNode(n, factorEvaluator.evaluateFactorForNode(n)));
        }
        return queuedNodes;
    }

}
