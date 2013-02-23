package edu.kpi.pzks.gui.layout;

import edu.kpi.pzks.gui.modelview.GraphView;
import edu.kpi.pzks.gui.modelview.NodeView;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * https://github.com/thechiselgroup/biomixer/blob/master/biomixer/src/org/thechiselgroup/biomixer/client/visualization_component/graph/layout/implementation/circle/CircleLayoutComputation.java
 */
public class CircleLayout implements Layout {

    private static double verticalPaddingPercent = 0.05;
    private static double horizontalPaddingPercent = 0.05;
    private final double maxAngle = 50;
    private final double minAngle = 3;

    @Override
    public void layout(GraphView graphView) {
        NodeView[] allNodes = graphView.getNodeViews().toArray(new NodeView[graphView.getNodeViews().size()]);
        if (allNodes.length < 1) {
            throw new IllegalArgumentException("GraphView must contain at least one node!");
        }

        // special case if there is just one node
        Rectangle graphBounds = graphView.getBounds();
        if (allNodes.length == 1) {
            NodeView singleNode = allNodes[0];
            Point topLeft = singleNode.getTopLeftForCentreAt((int) graphBounds.getCenterX(), (int) graphBounds.getCenterY());
            singleNode.setUpperLeftCorner(topLeft);
            return;
        }

        double angleBetweenNodes = getAngleBetweenNodes(allNodes);

        // get radius
        double graphWidth = graphBounds.getWidth();
        double graphHeight = graphBounds.getHeight();

        double radiusX = graphWidth / 2 - horizontalPaddingPercent * graphWidth - LayoutUtils.getMaxNodeWidth(allNodes) / 2;

        double radiusY = graphHeight / 2 - verticalPaddingPercent * graphHeight - LayoutUtils.getMaxNodeHeight(allNodes) / 2;

        // TODO: allow varying radius if radiusX and radiusY are not equal
        double radius = Math.min(radiusX, radiusY);

        for (int i = 0; i < allNodes.length; i++) {
            NodeView nodeView = allNodes[i];

            double nodeAngleRadians = Math.toRadians(minAngle + i * angleBetweenNodes);
            double deltaXFromGraphCentre = radius * Math.sin(nodeAngleRadians);
            double deltaYFromGraphCentre = -radius * Math.cos(nodeAngleRadians);

            Point graphCentre = new Point((int) graphBounds.getCenterX(), (int) graphBounds.getCenterY());
            int x = (int) (graphCentre.getX() + deltaXFromGraphCentre);
            int y = (int) (graphCentre.getY() + deltaYFromGraphCentre);

            Point topLeft = nodeView.getTopLeftForCentreAt(x, y);
            nodeView.setUpperLeftCorner(topLeft);
        }
    }

    private double getAngleBetweenNodes(NodeView[] allNodes) {
        double angleSpread = maxAngle - minAngle;
        if (angleSpread < 360.0) {
            /*
             * place a node at both maxAngle and minAngle because they normally
             * won't overlap
             */
            return angleSpread / (allNodes.length - 1);
        } else {
            /*
             * do not place a node at both maxAngle and minAngle because they
             * definitely will overlap
             */
            return angleSpread / allNodes.length;
        }
    }
}
