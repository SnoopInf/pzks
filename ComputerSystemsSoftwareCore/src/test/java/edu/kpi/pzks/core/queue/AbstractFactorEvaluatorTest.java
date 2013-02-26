package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.queue.factors.DecreasingConnectivitySecondaryFactorEvaluatorImpl;
import edu.kpi.pzks.core.queue.factors.StaticFactorEvaluatorImpl;
import junit.framework.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author smarx
 */
public class AbstractFactorEvaluatorTest {

    private AbstractFactorEvaluator fe;

    @Test
    public void testLinearWeight() {
        Node nodeA = new Node(1, 0);
        Node nodeB = new Node(7, 1);
        Node nodeC = new Node(3, 2);
        Collection<Node> nodes = Arrays.asList(new Node[]{nodeA, nodeB, nodeC});
        Collection<Link> links = Arrays.asList(new Link[]{new Link(nodeA, nodeB), new Link(nodeB, nodeC)});
        fe = new DecreasingConnectivitySecondaryFactorEvaluatorImpl(nodes);

        assertEquals(11, fe.getCriticalPathForGraph());
    }

    @Test
    public void testBottomCut() {
        Node nodeA = new Node(1, 0);
        Node nodeB = new Node(7, 1);
        Node nodeC = new Node(3, 2);
        Node nodeD = new Node(5, 3);
        List<Node> nodes = Arrays.asList(nodeA, nodeB, nodeC, nodeD);
        Collection<Link> links = Arrays.asList(new Link(nodeA, nodeB),
                new Link(nodeB, nodeC),
                new Link(nodeB, nodeD));
        LoopCheckCriticalPath(nodes, 13);
    }

    @Test
    public void testTopAnBottomCut() {
        Node nodeA = new Node(1, 0);   //   1   7
        Node nodeA2 = new Node(7, 1);  //   \  x
        Node nodeB = new Node(7, 2);   //    7
        Node nodeC = new Node(3, 3);   //   /x
        Node nodeD = new Node(5, 4);   //  3  5 ====> 19
        List<Node> nodes = Arrays.asList(nodeA, nodeA2, nodeB, nodeC, nodeD);
        Collection<Link> links = Arrays.asList(new Link(nodeA, nodeB),
                new Link(nodeA2, nodeB),
                new Link(nodeB, nodeC),
                new Link(nodeB, nodeD));

        LoopCheckCriticalPath(nodes, 19); //BUG! at rare cases, a critical path of 13 is returned
    }

    @Test
    public void testTopAnBottomCutWithComponents() {
        Node nodeA = new Node(1, 0);   //   1   7     1
        Node nodeA2 = new Node(7, 1);  //   \  x      |
        Node nodeB = new Node(7, 2);   //    7        5
        Node nodeC = new Node(3, 3);   //   /x
        Node nodeD = new Node(5, 4);   //  3  5 ====> 19
        Node nodeE = new Node(1, 5);
        Node nodeF = new Node(5, 6);

        List<Node> nodes = Arrays.asList(nodeA, nodeA2, nodeB, nodeC, nodeD, nodeE, nodeF);
        Collection<Link> links = Arrays.asList(new Link(nodeA, nodeB),
                new Link(nodeA2, nodeB),
                new Link(nodeB, nodeC),
                new Link(nodeB, nodeD),
                new Link(nodeE, nodeF)
        );

        LoopCheckCriticalPath(nodes, 19);
    }

    @Test
    public void testTopAnBottomCutWithHeavyComponents() {
        Node nodeA = new Node(1, 0);   //   1   7     1
        Node nodeA2 = new Node(7, 1);  //   \  x      |
        Node nodeB = new Node(7, 2);   //    7        5
        Node nodeC = new Node(3, 3);   //   /x
        Node nodeD = new Node(5, 4);   //  3  5 ====> 19
        Node nodeE = new Node(12, 5);
        Node nodeF = new Node(15, 6);

        List<Node> nodes = Arrays.asList(nodeA, nodeA2, nodeB, nodeC, nodeD, nodeE, nodeF);
        Collection<Link> links = Arrays.asList(new Link(nodeA, nodeB),
                new Link(nodeA2, nodeB),
                new Link(nodeB, nodeC),
                new Link(nodeB, nodeD),
                new Link(nodeE, nodeF)
        );

        LoopCheckCriticalPath(nodes, 27);
    }

    private void LoopCheckCriticalPath(List<Node> nodes, int weight) {
        for(int i = 0; i < 100; i++) {
            Collections.shuffle(nodes);
            fe = new DecreasingConnectivitySecondaryFactorEvaluatorImpl(nodes);
            assertEquals("Failed on i="+i, weight, fe.getCriticalPathForGraph());
        }
    }
}
