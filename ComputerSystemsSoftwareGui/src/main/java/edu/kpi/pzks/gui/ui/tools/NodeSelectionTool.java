package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.gui.ui.GraphPanel;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author Aloren
 */
public class NodeSelectionTool implements SelectionDraggingTool {

    private final GraphPanel graphPanel;

    public NodeSelectionTool(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public void paint(Graphics2D g2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
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

}
