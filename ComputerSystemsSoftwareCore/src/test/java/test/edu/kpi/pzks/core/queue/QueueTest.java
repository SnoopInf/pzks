package test.edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.queue.IdFactorEvaluatorImpl;
import edu.kpi.pzks.core.queue.SingleFactorQueue;
import edu.kpi.pzks.core.queue.QueuedNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author smarx
 */

public class QueueTest {

    @Test
    public void testQueueGenerationOnStaticIdEvaluator() {
        Graph graph = mock(Graph.class);

        when(graph.getNodes()).thenReturn(new HashSet<>(Arrays.asList(new Node[]{new Node(1, 1),
                new Node(1, 0), new Node(1, 4)})));

        SingleFactorQueue queue = new SingleFactorQueue(new IdFactorEvaluatorImpl(), graph.getNodes());

        Collection<QueuedNode> queuedNodeCollection = queue.evaluate();

        QueuedNode previous = null;
        for (QueuedNode current : queuedNodeCollection) {
            if (previous != null) {
                assertTrue(current.getQueueingFactor() >= previous.getQueueingFactor());
                assertTrue(current.getNode().getId() > previous.getNode().getId());
            }
            previous = current;
        }
    }
}
