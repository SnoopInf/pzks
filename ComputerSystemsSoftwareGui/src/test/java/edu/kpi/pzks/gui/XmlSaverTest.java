package edu.kpi.pzks.gui;

import edu.kpi.pzks.core.exceptions.GraphException;
import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Links;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.gui.io.GraphSaver;
import edu.kpi.pzks.gui.io.impl.XmlGraphSaver;
import edu.kpi.pzks.gui.modelview.GraphView;
import edu.kpi.pzks.gui.modelview.LinkView;
import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.modelview.impl.GraphViewImpl;
import edu.kpi.pzks.gui.modelview.impl.LinkViewImpl;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 *
 * @author Aloren
 */
public class XmlSaverTest {

    int size = 5;
    Set<Node> nodes;
    Set<Link> links;
    Collection<LinkView> linkViews;
    Collection<NodeView> nodeViews;

    @Test
    public void testSave() {
        nodes = getNodes();
        links = getLinks(nodes);
        linkViews = createLinkViewsCollection();
        nodeViews = createNodeViewsCollection();

        GraphSaver saver = new XmlGraphSaver();
        File file = new File("test.xml");
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException ex) {
            fail("Exception while creating new file!");
        }
        GraphView graphView = mock(GraphViewImpl.class);
        Graph graph = mock(Graph.class);
        when(graph.getLinks()).thenReturn(new Links(links));
        when(graph.getNodes()).thenReturn(nodes);
        when(graphView.getGraph()).thenReturn(graph);

        when(graphView.getLinkViews()).thenReturn(linkViews);
        when(graphView.getNodeViews()).thenReturn(nodeViews);
        try {
            saver.saveToFile(graphView, file);
        } catch (GraphException ex) {
            fail("Exception while saving graph to file!");
        }
        //TODO add validation

        file.delete();
    }

    private Set<Node> getNodes() {
        Set<Node> nodes = new HashSet<>();
        for (int i = 0; i < size; i++) {
            nodes.add(new Node());
        }
        return nodes;
    }

    private Set<Link> getLinks(Set<Node> nodes) {
        Set<Link> links = new HashSet<>();
        Node[] nodesAr = nodes.toArray(new Node[nodes.size()]);
        for (int i = 0; i < size; i++) {
            Node fromNode = nodesAr[i];
            Node toNode = (i + 1 == size) ? nodesAr[0] : nodesAr[i + 1];
            links.add(new Link(fromNode, toNode));
        }
        return links;
    }

    private Collection<LinkView> createLinkViewsCollection() {
        Collection<LinkView> linkViews = new LinkedList<>();
        Link[] linksAr = links.toArray(new Link[links.size()]);
        for (int i = 0; i < size; i++) {
            LinkView linkView = mock(LinkViewImpl.class);
            when(linkView.getLink()).thenReturn(linksAr[i]);
            when(linkView.getBendPoint()).thenReturn(new Point(i * 5, i * 5));
            linkViews.add(linkView);
        }
        return linkViews;
    }

    private Collection<NodeView> createNodeViewsCollection() {
        Collection<NodeView> nodeViews = new LinkedList<>();
        Node[] nodesAr = nodes.toArray(new Node[nodes.size()]);
        for (int i = 0; i < size; i++) {
            NodeView nodeView = mock(NodeView.class);
            when(nodeView.getNode()).thenReturn(nodesAr[i]);
            when(nodeView.getUpperLeftCorner()).thenReturn(new Point(i, i));
            nodeViews.add(nodeView);
        }
        return nodeViews;
    }
}
