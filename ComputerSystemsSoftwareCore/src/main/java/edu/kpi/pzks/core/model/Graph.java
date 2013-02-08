package edu.kpi.pzks.core.model;

import edu.kpi.pzks.core.listener.IChangeListener;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
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
            links.removeAllLinksFor(node);
            nodes.remove(node);
            notifyListener();
        }
    }

    public void addLink(Link link) {
        if (link != null) {
            links.add(link);
            link.addToOutputNodes();
            link.addToInputNodes();
        }
    }

    public void removeLink(Link link) {
        if (link != null) {
            links.remove(link);
            link.removeFromOutputNodes();
            link.removeFromInputNodes();
        }
    }

    //TODO please move me to gui
//    public boolean isAnyGraphObjectSelected() {
//        return !selectedNodes.isEmpty();
//    }
//
//    public ArrayList<GraphObject> getSelectedNodes() {
//        return this.selectedNodes;
//    }
//    public void setSelectedGraphObjects(ArrayList<GraphObject> selected) {
//        deselectSelectedGraphObjects();
//        this.selectedNodes.addAll(selected);
//        for (GraphObject o : selected) {
//            o.setSelected(true);
//        }
//    }
//
//    public void setSelectedGraphObject(GraphObject graphObject) {
//        if (graphObject != null) {
//            deselectSelectedGraphObjects();
//            selectedNodes.add(graphObject);
//            graphObject.setSelected(true);
//        }
//    }
//
//    public void addSelectedGraphObject(GraphObject pn) {
//        if (pn != null) {
//            selectedNodes.add(pn);
//            pn.setSelected(true);
//        }
//    }
//
//    public void deselectSelectedGraphObject(GraphObject pn) {
//        if (selectedNodes.contains(pn)) {
//            selectedNodes.removeAllLinksFor(pn);
//            pn.setSelected(false);
//        }
//    }
//
//    public void deselectSelectedGraphObjects() {
//        for (int i = 0; i < selectedNodes.size(); i++) {
//            selectedNodes.get(i).setSelected(false);
//        }
//        selectedNodes.clear();
//    }
//
//    public void removeSelectedGraphObjects() {
//        for (int i = 0; i < selectedNodes.size(); i++) {
//            selectedNodes.get(i).setSelected(false);
//            removeGraphObject(selectedNodes.get(i));
//        }
//        selectedNodes.clear();
//    }
    /**
     * Removes node from the graph and the links that are connected with it.
     *
     * @param pn node to removeAllLinksFor from the graph.
     */
//    public ArrayList<GraphObject> getNodesInsideRectangle(Rectangle rect) {
//        ArrayList<GraphObject> selected = new ArrayList<>();
//        for (Node node : nodes) {
//            if (rect.contains(node.getPoint())) {
//                selected.add(node);
//            }
//        }
//        for (int i = 0; i < links.size(); i++) {
//            for (int j = 0; j < links.get(i).getLines().length; j++) {
//                Link link = links.get(i);
//                if (rect.contains(link.getX1(), link.getY1()) && rect.contains(link.getX2(), link.getY2())) {
//                    selected.add(link);
//                }
//            }
//        }
//        return selected;
//    }
//    /**
//     * Returns the node that contains the input point.
//     *
//     * @param xx
//     * @param yy
//     * @return
//     */
//    public GraphObject getNodeAtPoint(int xx, int yy) throws NullPointerException {
//        for (Node node : nodes) {
//            if (node.contains(xx, yy)) {
//                return node;
//            }
//        }
//        for (int i = 0; i < links.size(); i++) {
//            for (int j = 0; j < links.get(i).getLines().length; j++) {
//                if (links.get(i).contains(xx, yy)) {
//                    return links.get(i);
//                }
//            }
//        }
//        return null;
//
//    }
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
