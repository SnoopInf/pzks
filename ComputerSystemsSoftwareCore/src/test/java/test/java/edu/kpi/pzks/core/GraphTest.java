package test.java.edu.kpi.pzks.core;

import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Aloren
 */
public class GraphTest {

    Graph g;
    int nodesSize = 10;
    int linksSize = 10;
    List<Node> nodes;
    List<Link> links;

    //TODO refactor
    @Before
    public void before() {
        g = new Graph(true);
        nodes = new LinkedList<>();
        links = new LinkedList<>();

        g.addNode(null);
        assertTrue("", g.isNodesEmpty());
        createNodes();
        createLinks();

        addNodesToGraph();
        assertTrue("", g.getNodes().size() == nodesSize);

        addLinksGraph();
        assertTrue("", g.getLinks().size() == linksSize);
    }

    @Test
    public void testGraph() {
        removeNodesFromGraph();
        assertTrue("", g.isNodesEmpty());
        removeLinksFromNode();
        assertTrue("", g.isLinksEmpty());
    }

    private void createNodes() {
        for (int i = 0; i < nodesSize; i++) {
            Node n = new Node();
            nodes.add(n);
        }
    }

    private void createLinks() {
        for (int i = 0; i < linksSize; i++) {
            Link link = new Link(new Node(), new Node());
            links.add(link);
        }
    }

    private void addNodesToGraph() {
        for (int i = 0; i < nodesSize; i++) {
            Node n = nodes.get(i);
            g.addNode(n);
        }
    }

    private void addLinksGraph() {
        for (int i = 0; i < linksSize; i++) {
            Link link = links.get(i);
            g.addLink(link);
        }
    }

    private void removeNodesFromGraph() {
        for (int i = 0; i < nodesSize; i++) {
            Node n = nodes.get(i);
            g.removeNode(n);
        }
    }

    private void removeLinksFromNode() {
        for (int i = 0; i < linksSize; i++) {
            Link link = links.get(i);
            g.removeLink(link);
        }
    }
}
