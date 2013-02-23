package edu.kpi.pzks.core.model;

import edu.kpi.pzks.core.listener.IChangeListener;
import edu.kpi.pzks.core.validator.Validator;

import java.io.Serializable;
import java.util.Collection;
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
    private transient Set<Validator> validators = new HashSet<>();
    private boolean isOriented;
    private int incrementer;

    public Graph(boolean isOriented) {
        this.isOriented = isOriented;
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

    public void addValidator(Validator validator) {
        validators.add(validator);
    }

    public void removeAllValidators() {
        validators.clear();
    }

    public void removeValidator(Validator validator) {
        validators.remove(validator);
    }

    /**
     * Validates the graph with its validators.
     */
    public void validate() {
        for (Validator validator : validators) {
            validator.validate(nodes, links);
        }
    }

    public void addNode(Node node) {
        if (node != null) {
            nodes.add(node);
            node.setId(incrementer++);
            notifyListener();
        }
    }

    public void addNodes(Collection<Node> nodesToAdd) {
        for (Node node : nodesToAdd) {
            addNode(node);
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

    public void addLinks(Collection<Link> linksToAdd) {
        for (Link link : linksToAdd) {
            addLink(link);
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

    public boolean isOriented() {
        return isOriented;
    }

    public void setOriented(boolean oriented) {
        isOriented = oriented;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph: ");
        sb.append("\r\nNodes: ");
        for (Node node : nodes) {
            sb.append(node).append("\r\n");
        }
        sb.append("Links: ");
        for (Link link : links) {
            sb.append(link).append("\r\n");

        }
        return sb.toString();
    }
}
