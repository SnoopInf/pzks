package edu.kpi.pzks.gui.modelview;

import edu.kpi.pzks.core.model.Link;
import java.awt.Point;

/**
 *
 * @author Aloren
 */
public interface LinkView extends GraphObjectView {

    Link getLink();

    void setBendPoint(int x, int y);

    Point getBendPoint();

    NodeView getFromNodeView();

    NodeView getToNodeView();
    
    void setFromNodeView(NodeView nodeView);
    
    void setToNodeView(NodeView nodeView);
    
    void setLink(Link link);
}
