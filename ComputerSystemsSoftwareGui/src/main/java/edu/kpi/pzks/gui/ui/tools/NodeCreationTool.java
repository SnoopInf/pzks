package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.modelview.impl.NodeViewImpl;
import edu.kpi.pzks.gui.ui.GraphPanel;
import edu.kpi.pzks.gui.utils.COLORS;
import edu.kpi.pzks.gui.utils.CONSTANTS;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Aloren
 */
public class NodeCreationTool implements Tool {

    protected Point myMousePosition = new Point(0, 0);
    protected boolean needToPaint;
    protected final GraphPanel graphPanel;

    public NodeCreationTool(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    //TODO copypaste from NodeView paint
    //Do we need to put here tempNodeView and call its paint method instead?
    @Override
    public void paint(Graphics2D g2) {
        if (needToPaint) {
            paintNode(g2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        createNode(me);
        graphPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        needToPaint = true;
    }

    @Override
    public void mouseExited(MouseEvent me) {
        needToPaint = false;
        graphPanel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
//        final GraphPanel graphPanel = mainFrame.getActivePanel();
//        Point2D.Double p = graphPanel.getGrid().getSnapToGridPoint(me.getX(), me.getY());
//        myMousePosition.x = (int) p.x;
//        myMousePosition.y = (int) p.y;
//        graphPanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        if (needToPaint) {
            myMousePosition = graphPanel.getGrid().getSnapToGridPoint(me.getX(), me.getY());
            graphPanel.repaint();
        }
    }

    protected void createNode(MouseEvent me) {
        Point point = graphPanel.getGrid().getSnapToGridPoint(me.getX(), me.getY());
        NodeView nodeView = createNodeView(point);
        graphPanel.getGraphView().addNodeView(nodeView);
        graphPanel.checkGraphIsValid();
    }

    private void paintNodeBorder(Graphics2D g2) {
        g2.setColor(COLORS.NODE_COLOR);
        fillShape(g2);
    }

    private void paintNodeInside(Graphics2D g2) {
        g2.setColor(COLORS.NODE_BORDER_COLOR);
        g2.setStroke(new BasicStroke(1.5f));
        drawShape(g2);
    }

    private void paintNode(Graphics2D g2) {
        paintNodeBorder(g2);
        paintNodeInside(g2);
    }

    protected NodeView createNodeView(Point point) {
        NodeView nodeView = new NodeViewImpl(new Node(), point.x, point.y);
        return nodeView;
    }

    protected void fillShape(Graphics2D g2) {
        g2.fillOval(myMousePosition.x, myMousePosition.y,
                CONSTANTS.NODE_WIDTH, CONSTANTS.NODE_HEIGHT);
    }

    protected void drawShape(Graphics2D g2) {
        g2.drawOval(myMousePosition.x, myMousePosition.y,
                CONSTANTS.NODE_WIDTH, CONSTANTS.NODE_HEIGHT);
    }
}
