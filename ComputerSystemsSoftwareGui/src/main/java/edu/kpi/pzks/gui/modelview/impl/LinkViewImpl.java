package edu.kpi.pzks.gui.modelview.impl;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.gui.modelview.LinkView;
import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.utils.COLORS;
import edu.kpi.pzks.gui.utils.PaintUtils;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JPopupMenu;

/**
 *
 * @author asmirnova
 */
public class LinkViewImpl implements LinkView {

    protected NodeView fromNodeView;
    protected NodeView toNodeView;
    // If no bendPoint then it is (-1;-1)
    protected Point bendPoint;
    protected Link link;
    private static final int HIT_BOX_SIZE = 6;

    public LinkViewImpl(Link link) {
        this(null, null, link);
    }

    public LinkViewImpl(NodeView fromNodeView, NodeView toNodeView, Link link) {
        this.link = link;
        this.fromNodeView = fromNodeView;
        this.toNodeView = toNodeView;
        this.bendPoint = new Point(-1, -1);
    }

    @Override
    public Link getLink() {
        return this.link;
    }

    @Override
    public void setBendPoint(int x, int y) {
        this.bendPoint = new Point(x, y);
    }

    @Override
    public Point getBendPoint() {
        return bendPoint;
    }

    @Override
    public NodeView getFromNodeView() {
        return fromNodeView;
    }

    @Override
    public void setFromNodeView(NodeView fromNodeView) {
        this.fromNodeView = fromNodeView;
    }

    @Override
    public NodeView getToNodeView() {
        return toNodeView;
    }

    @Override
    public void setToNodeView(NodeView toNodeView) {
        this.toNodeView = toNodeView;
    }

    @Override
    public void paint(Graphics2D g2) {
        final int srcX = (int) (fromNodeView.getCenter().getX());
        final int srcY = (int) (fromNodeView.getCenter().getY());

        final int destX = (int) (toNodeView.getCenter().getX());
        final int destY = (int) (toNodeView.getCenter().getY());

        g2.setColor(COLORS.LINK_COLOR);
        if (!hasBendPoint()) {
            drawWeightWithoutBendPoint(g2, srcX, destX, srcY, destY);
            g2.drawLine(srcX, srcY, destX, destY);
            double aDir = Math.atan2(srcX - destX, srcY - destY);
            drawArrowHead(g2, aDir, srcX, srcY, destX, destY);
        } else {
            drawWeightWithBendPoint(g2);
            g2.drawLine(srcX, srcY, bendPoint.x, bendPoint.y);
            g2.drawLine(bendPoint.x, bendPoint.y, destX, destY);
            double aDir = Math.atan2(bendPoint.getX() - destX, bendPoint.getY() - destY);
            drawArrowHead(g2, aDir, (int) bendPoint.getX(), (int) bendPoint.getY(), destX, destY);
        }
        g2.setStroke(new BasicStroke(1));
    }

    /**
     * Draws arrow-head of the link.
     */
    private void drawArrowHead(Graphics2D g2, double aDir, int srcX, int srcY, int destX, int destY) {
        double dx = PaintUtils.getLength(srcX, destX);
        double dy = PaintUtils.getLength(srcY, destY);
        double theta = Math.atan2(dy, dx);
        double reverseTheta = (theta > 0.0d) ? (theta - Math.PI) : (theta + Math.PI);
        Point2D.Double destIntersectionP = PaintUtils.getEllipseIntersectionPoint(reverseTheta,
                toNodeView.getWidth(), toNodeView.getHeight());
        int x = (int) (destX + destIntersectionP.getX());
        int y = (int) (destY + destIntersectionP.getY());

        g2.setStroke(new BasicStroke(1f));
        Polygon arrowHead = new Polygon();
        float stroke = 0.2f;
        int i1 = 12 + (int) (stroke * 2);
        int i2 = 6 + (int) stroke;

        arrowHead.addPoint(x, y);// arrow tip
        arrowHead.addPoint(x + PaintUtils.xCor(i1, aDir + 0.5), y + PaintUtils.yCor(i1, aDir + 0.5));
        arrowHead.addPoint(x + PaintUtils.xCor(i2, aDir), y + PaintUtils.yCor(i2, aDir));
        arrowHead.addPoint(x + PaintUtils.xCor(i1, aDir - 0.5), y + PaintUtils.yCor(i1, aDir - 0.5));
        arrowHead.addPoint(x, y);// arrow tip
        g2.drawPolygon(arrowHead);
        g2.fillPolygon(arrowHead);

    }

    @Override
    public JPopupMenu getPopupMenu() {
        throw new UnsupportedOperationException("Not supported yet.");
//        return new LinkPopup(this);
    }

    @Override
    public boolean contains(int x, int y) {
        int boxX = x - HIT_BOX_SIZE / 2;
        int boxY = y - HIT_BOX_SIZE / 2;
        int width = HIT_BOX_SIZE;
        int height = HIT_BOX_SIZE;
        final Line2D.Double[] lines = getLines();
        for (int j = 0; j < lines.length; j++) {
            Line2D.Double line = lines[j];
            if (line.intersects(boxX, boxY, width, height)) {
                return true;
            }
        }
        return false;
    }

    private Line2D.Double[] getLines() {
        Point fromCenter = fromNodeView.getCenter();
        Point toCenter = toNodeView.getCenter();
        if (hasBendPoint()) {
            return new Line2D.Double[]{new Line2D.Double(toCenter, bendPoint),
                        new Line2D.Double(bendPoint, fromCenter)};
        } else {
            return new Line2D.Double[]{new Line2D.Double(toCenter, fromCenter)};
        }
    }

    private boolean hasBendPoint() {
        return !(bendPoint.x == -1 && bendPoint.y == -1);
    }

    @Override
    public void setLink(Link link) {
        this.link = link;
    }

    private void drawWeightWithoutBendPoint(Graphics2D g2, final int srcX, final int destX, final int srcY, final int destY) {
        g2.drawString(Double.toString(link.getWeight()), (srcX + destX) / 2 + 10, (srcY + destY) / 2 + 10);
    }

    private void drawWeightWithBendPoint(Graphics2D g2) {
        g2.drawString(Double.toString(link.getWeight()), (int) bendPoint.getX() + 10, (int) bendPoint.getY() + 10);
    }
}
