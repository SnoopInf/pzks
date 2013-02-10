package edu.kpi.pzks.core.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Class that incapsulates all links inside the graph.
 *
 * @author Aloren
 */
public class Links extends HashSet<Link> {

    private static final long serialVersionUID = 2;

    public Links() {
        super(10);
    }

    public Links(Set<Link> links) {
        super(links);
    }

    @Override
    public boolean add(Link link) {
        return super.add(link);
    }

    //TODO is it possible to have more than 1 link between 2 nodes?
    public void removeLinkBetweenNodes(Node node1, Node node2) {
        List<Link> linksToRemove = new LinkedList<>();
        for (Link link : this) {
            if (link.getFromNode().equals(node1)
                    && link.getToNode().equals(node2)) {
                linksToRemove.add(link);
            }
        }
        super.removeAll(linksToRemove);
    }

    public boolean remove(Link link) {
        return super.remove(link);
    }

    public void removeAllLinksFor(Node node) {
        List<Link> linksToRemove = new LinkedList<>();
        for (Link link : this) {
            if (link.getFromNode().equals(node)
                    || link.getToNode().equals(node)) {
                linksToRemove.add(link);
            }
        }
        super.removeAll(linksToRemove);
    }

    public boolean contains(Link link) {
        return super.contains(link);
    }
}
