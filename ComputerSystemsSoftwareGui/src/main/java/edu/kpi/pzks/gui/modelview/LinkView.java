package edu.kpi.pzks.gui.modelview;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.gui.ui.popups.LinkViewPopup;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * @author Aloren
 */
public interface LinkView extends GraphObjectView {

    Link getLink();

    void setBendPoint(int x, int y);

    Point getBendPoint();

    boolean hasBendPoint();

    NodeView getFromNodeView();

    NodeView getToNodeView();

    void setFromNodeView(NodeView nodeView);

    void setToNodeView(NodeView nodeView);

    void setLink(Link link);

    Line2D.Double[] getLines();

    LinkViewPopup getPopup();

    void setOriented(boolean oriented);
}
