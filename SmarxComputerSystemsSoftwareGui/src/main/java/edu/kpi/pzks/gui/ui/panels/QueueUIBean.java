package edu.kpi.pzks.gui.ui.panels;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class QueueUIBean {
    private final Set<Node> nodes;
    private final Collection<Link> links;

    public QueueUIBean(Set<Node> nodes, Collection<Link> links) {
        this.nodes = nodes;
        this.links = links;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public Collection<Link> getLinks() {
        return links;
    }
}