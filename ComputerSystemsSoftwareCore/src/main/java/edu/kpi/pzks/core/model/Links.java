package edu.kpi.pzks.core.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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

    public void removeLinkBetweenNodes(Node node1, Node node2) {
        LinkedList<Link> linksToRemove = new LinkedList<>();
        Iterator<Link> it = iterator();
        while (it.hasNext()) {
            Link link = it.next();
            if (link.getFromNode().equals(node1)
                    && link.getFromNode().equals(node2)) {
                linksToRemove.add(link);
            }
        }
        super.removeAll(linksToRemove);
    }

    public boolean remove(Link link) {
        return super.remove(link);
    }

    public void removeAllLinksFor(Node node) {
        LinkedList<Link> linksToRemove = new LinkedList<>();
        Iterator<Link> it = iterator();
        while (it.hasNext()) {
            Link link = it.next();
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
