package edu.kpi.pzks.gui.modelview.impl;

import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.gui.utils.CONSTANTS;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Aloren
 */
public class SystemNodeViewImpl extends NodeViewImpl {

    public SystemNodeViewImpl(Node node, Point2D.Double point) {
        this(node, point.x, point.y);
    }

    public SystemNodeViewImpl(Node node) {
        this(node, 0, 0);
    }

    public SystemNodeViewImpl(Node node, double x, double y) {
        super(node);
        this.shape = new Rectangle2D.Double(x, y,
                CONSTANTS.NODE_WIDTH, CONSTANTS.NODE_HEIGHT);
    }
}
