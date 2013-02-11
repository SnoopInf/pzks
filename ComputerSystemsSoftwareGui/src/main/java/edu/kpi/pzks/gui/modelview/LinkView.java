package edu.kpi.pzks.gui.modelview;

import edu.kpi.pzks.core.model.Link;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Aloren
 */
public interface LinkView extends GraphObjectView {

    void paintWithoutColor(Graphics2D g2);

    Link getLink();

    void setBendPoint(int x, int y);

    Point getBendPoint();

    boolean hasBendPoint();

    NodeView getFromNodeView();

    NodeView getToNodeView();

    void setFromNodeView(NodeView nodeView);

    void setToNodeView(NodeView nodeView);

    void setLink(Link link);
}
