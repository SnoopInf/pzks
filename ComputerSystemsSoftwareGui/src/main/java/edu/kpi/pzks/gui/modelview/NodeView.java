package edu.kpi.pzks.gui.modelview;

import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.gui.ui.popups.NodeViewPopup;

import java.awt.*;

/**
 * @author Aloren
 */
public interface NodeView extends GraphObjectView {

    Point getUpperLeftCorner();

    Point getCenter();

    void setUpperLeftCorner(Point point);

    int getWidth();

    int getHeight();

    String getName();

    Node getNode();

    NodeViewPopup getPopup();

    Point getTopLeftForCentreAt(int x, int y);
}
