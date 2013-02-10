package test.java.edu.kpi.pzks.core;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Links;
import edu.kpi.pzks.core.model.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kirill Davidenko
 */
public class LinksTest {

    Links links;
    Node node1;
    Node node2;
    Node node3;
    Node node4;

    Link link12;
    Link link13;
    Link link34;

    @Before
    public void init() {
        links = new Links();

        node1 = new Node();
        node2 = new Node();
        node3 = new Node();
        node4 = new Node();

        link12 = new Link(node1, node2);
        link13 = new Link(node1, node3);
        link34 = new Link(node3, node4);

        links.add(link12);
        links.add(link13);
        links.add(link34);
    }

    @Test
    public void testRemoveLinkForNode() {
        links.removeAllLinksFor(node3);
        assertFalse(links.contains(link13));
        assertFalse(links.contains(link34));
        assertEquals(1, links.size());
    }

    @Test
    public void testRemoveLinkBetweenNodes() {
        links.removeLinkBetweenNodes(node1, node2);
        assertFalse(links.contains(link12));
        assertTrue(links.contains(link13));
        assertEquals(2, links.size());
    }

}
