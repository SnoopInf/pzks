package edu.kpi.pzks.core.model;

import edu.kpi.pzks.core.exceptions.ValidationException;
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
     * Safe validation - no exception thrown I recommend to use this method in
     * UI actions
     */
    public boolean isValid() {
        boolean isValid = true;
        for (Validator validator : validators) {
            isValid = isValid & validator.isValid(nodes, links);
        }
        return isValid;
    }


    public void addNode(Node node) {
        if (node != null) {
            nodes.add(node);
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
}
