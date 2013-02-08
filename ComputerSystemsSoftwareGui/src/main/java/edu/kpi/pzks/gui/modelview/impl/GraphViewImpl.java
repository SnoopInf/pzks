package edu.kpi.pzks.gui.modelview.impl;

import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.core.model.GraphObject;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.gui.modelview.GraphObjectView;
import edu.kpi.pzks.gui.modelview.GraphView;
import java.awt.Graphics;

/**
 *
 * @author asmirnova
 */
public class GraphViewImpl implements GraphView {

    public void paint(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Graph getGraph() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Node getNodeAtPoint(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Link getLinkAtPoint(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public GraphObjectView addViewByElement(GraphObject element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public GraphObjectView getViewByElement(GraphObject element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public GraphObjectView removeViewByElement(GraphObject element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
