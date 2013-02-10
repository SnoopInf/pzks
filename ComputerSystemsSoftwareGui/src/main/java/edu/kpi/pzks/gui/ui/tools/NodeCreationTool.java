package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.modelview.impl.NodeViewImpl;
import edu.kpi.pzks.gui.ui.GraphPanel;
import edu.kpi.pzks.gui.utils.COLORS;
import edu.kpi.pzks.gui.utils.CONSTANTS;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author Aloren
 */
public class NodeCreationTool implements Tool {

    private Point myMousePosition;
    private boolean needToPaint;
    private final GraphPanel graphPanel;

    public NodeCreationTool(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        this.myMousePosition = new Point(0, 0);
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
        NodeView nodeView = new NodeViewImpl(new Node(), point.x, point.y);
        graphPanel.getGraphView().addNodeView(nodeView);
    }

    private void paintNodeBorder(Graphics2D g2) {
        g2.setColor(COLORS.NODE_COLOR);
        g2.fillOval(myMousePosition.x, myMousePosition.y,
                CONSTANTS.NODE_WIDTH, CONSTANTS.NODE_HEIGHT);
    }

    private void paintNodeInside(Graphics2D g2) {
        g2.setColor(COLORS.NODE_BORDER_COLOR);
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawOval(myMousePosition.x, myMousePosition.y,
                CONSTANTS.NODE_WIDTH, CONSTANTS.NODE_HEIGHT);
    }

    private void paintNode(Graphics2D g2) {
        paintNodeBorder(g2);
        paintNodeInside(g2);
    }
}
