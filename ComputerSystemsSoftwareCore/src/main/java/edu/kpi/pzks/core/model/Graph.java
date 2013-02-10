package edu.kpi.pzks.core.model;

import edu.kpi.pzks.core.listener.IChangeListener;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Aloren
 */
public class Graph implements Serializable {

    private static final long serialVersionUID = 1;
    private Set<Node> nodes = new HashSet<>();
    private Links links = new Links();
    private transient Set<IChangeListener> changeListeners = new HashSet<>();

    public Graph() {
    }

    public void notifyListener() {
        for (IChangeListener listener : changeListeners) {
            listener.notifyChanged();
        }
    }

    public void addChangeListener(IChangeListener listener) {
        changeListeners.add(listener);
    }

    public Set<IChangeListener> getChangeListeners() {
        return changeListeners;
    }

    public void addNode(Node node) {
        if (node != null) {
            nodes.add(node);
            notifyListener();
        }
    }

    public void removeNode(Node node) {
        if (node != null) {
            links.removeAllLinksForNode(node);
            nodes.remove(node);
            notifyListener();
        }
    }

    public void addLink(Link link) {
        if (link != null) {
            links.add(link);
        }
    }

    public void removeLink(Link link) {
        if (link != null) {
            links.remove(link);
        }
    }

    public Links getLinks() {
        return links;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public boolean isNodesEmpty() {
        return nodes.isEmpty();
    }

    public boolean isLinksEmpty() {
        return links.isEmpty();
    }
}
