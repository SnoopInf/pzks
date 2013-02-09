package edu.kpi.pzks.gui.modelview.impl;

import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.gui.modelview.GraphView;
import edu.kpi.pzks.gui.modelview.LinkView;
import edu.kpi.pzks.gui.modelview.NodeView;
import java.awt.Graphics;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author asmirnova
 */
public class GraphViewImpl implements GraphView {

    private Graph graph;
    private Set<NodeView> nodeViews = new HashSet<>();
    private Set<LinkView> linkViews = new HashSet<>();

    public GraphViewImpl(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void paint(Graphics g) {
        for(NodeView nodeView : nodeViews) {
            nodeView.paint(g);
        }
        for(LinkView linkView : linkViews) {
            linkView.paint(g);
        }
    }

    @Override
    public Graph getGraph() {
        return this.graph;
    }

    @Override
    public void addNodeView(NodeView nodeView) {
        graph.addNode(nodeView.getNode());
        nodeViews.add(nodeView);
    }

    @Override
    public void addLinkView(LinkView linkView) {
        graph.addLink(linkView.getLink());
        linkViews.add(linkView);
    }

    @Override
    public NodeView getNodeView(Node node) {
        for(NodeView nodeView : nodeViews) {
            if(nodeView.getNode().equals(node)) {
                return nodeView;
            }
        }
        return null;
    }

    @Override
    public LinkView getLinkView(Link link) {
        for(LinkView linkView : linkViews) {
            if(linkView.getLink().equals(link)) {
                return linkView;
            }
        }
        return null;
    }

    @Override
    public void removeNode(Node node) {
        for(NodeView nodeView : nodeViews) {
            if(nodeView.getNode().equals(node)) {
                nodeViews.remove(nodeView);
                graph.removeNode(node);
                return;
            }
        }
    }

    @Override
    public void removeLink(Link link) {
        for(LinkView linkView : linkViews) {
            if(linkView.getLink().equals(link)) {
                linkViews.remove(linkView);
                graph.removeLink(link);
                return;
            }
        }
    }

    @Override
    public void removeNodeView(NodeView nodeView) {
        nodeViews.remove(nodeView);
    }

    @Override
    public void removeLinkView(LinkView linkView) {
        linkViews.remove(linkView);
    }

    @Override
    public NodeView getNodeViewAtPoint(int x, int y) {
        for(NodeView nodeView : nodeViews) {
            if(nodeView.contains(x, y)) {
                return nodeView;
            }
        }
        return null;
    }

    @Override
    public LinkView getLinkViewAtPoint(int x, int y) {
        for(LinkView linkView : linkViews) {
            if(linkView.contains(x, y)) {
                return linkView;
            }
        }
        return null;
    }

    @Override
    public Collection<LinkView> getLinkViews() {
        return linkViews;
    }

    @Override
    public Collection<NodeView> getNodeViews() {
        return nodeViews;
    }
}
