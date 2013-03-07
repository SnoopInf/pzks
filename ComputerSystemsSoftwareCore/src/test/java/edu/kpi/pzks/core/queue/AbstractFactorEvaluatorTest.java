package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.queue.factors.AbstractFactorEvaluator;
import edu.kpi.pzks.core.queue.factors.impl.DecreasingConnectivitySecondaryFactorEvaluatorImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

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
        fe = new DecreasingConnectivitySecondaryFactorEvaluatorImpl(nodes, links);

        assertEquals(11, fe.getCriticalPathWeightForGraph());
        assertEquals(11, fe.getCriticalPathFromTopTo(nodeA));
        assertEquals(10, fe.getCriticalPathFromTopTo(nodeB));
        assertEquals(3, fe.getCriticalPathFromTopTo(nodeC));
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
        LoopCheckCriticalPath(nodes, links, 13);
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

        LoopCheckCriticalPath(nodes, links, 19);
    }

    /**
     * The graph reflected in code below is a courtesy of Rusanova et al. ;-)
     * Source: Lecture no. 1
     */
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

        LoopCheckCriticalPath(nodes, links, 19);
    }

    @Test
    public void testTopAnBottomCutWithHeavyComponents() {
        Node nodeA = new Node(1, 0);   //   1   7     12
        Node nodeA2 = new Node(7, 1);  //   \  x      |
        Node nodeB = new Node(7, 2);   //    7        15 ===> 27
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

        LoopCheckCriticalPath(nodes, links, 27);
    }

    @Test
    public void testWeightedCriticalToLeafOnPathEqualsOne() throws Exception {
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

        fe = new DecreasingConnectivitySecondaryFactorEvaluatorImpl(nodes, links);
        assertEquals(fe.getCriticalPathWeightForGraph(), fe.getCriticalPathFromTopTo(nodeA2));
        assertTrue("Weighted critical path of leaf node on critical path must be equal to 1",
                Double.compare(1.0, fe.getWeightedCriticalPathFromTopTo(nodeA2)) == 0);
    }

    @Test
    public void testCriticalNumber() throws Exception {
        Node node1 = new Node(2, 1);
        Node node2 = new Node(3, 2);
        Node node4 = new Node(3, 4);
        Node node6 = new Node(1, 6);
        Node node7 = new Node(1, 6);
        Node node8 = new Node(4, 8);

        Node node3 = new Node(1, 3);
        Node node5 = new Node(5, 5);

        List<Node> nodes = Arrays.asList(node1, node2, node4, node6, node7, node3, node5);
        Collection<Link> links = Arrays.asList(new Link(node1, node4),
                new Link(node2, node4),
                new Link(node4, node6),
                new Link(node6, node7),
                new Link(node6, node8),
                new Link(node3, node5)
        );

        fe = new DecreasingConnectivitySecondaryFactorEvaluatorImpl(nodes, links);
        assertEquals(4, fe.getCriticalNumberFromTopTo(node1));
        assertEquals(4, fe.getCriticalNumberFromTopTo(node2));
        assertEquals(2, fe.getCriticalNumberFromTopTo(node3));
        assertEquals(3, fe.getCriticalNumberFromTopTo(node4));
        assertEquals(1, fe.getCriticalNumberFromTopTo(node5));
        assertEquals(2, fe.getCriticalNumberFromTopTo(node6));
        assertEquals(1, fe.getCriticalNumberFromTopTo(node7));
        assertEquals(1, fe.getCriticalNumberFromTopTo(node8));
    }

    private void LoopCheckCriticalPath(List<Node> nodes, Collection<Link> links, int weight) {
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(nodes);
            fe = new DecreasingConnectivitySecondaryFactorEvaluatorImpl(nodes, links);
            assertEquals("Failed on i=" + i, weight, fe.getCriticalPathWeightForGraph());
        }
    }
}
