package edu.kpi.pzks.gui.modelview;

import edu.kpi.pzks.core.model.Node;
import java.awt.Point;

/**
 *
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
}
