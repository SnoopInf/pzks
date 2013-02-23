package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.gui.modelview.LinkView;
import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.ui.panels.GraphPanel;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * Lot of fooooo code. Sorry
 * TODO refactor!!!
 * @author Aloren
 */
public class LinkDraggingTool implements SelectionDraggingTool {

    private final GraphPanel graphPanel;
    private Point mousePosition = new Point(0, 0);
    private LinkView selectedLinkView;
    private NodeView selectedNodeView;

    public LinkDraggingTool(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public void paint(Graphics2D g2) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        selectedLinkView = graphPanel.getGraphView().getLinkViewAtPoint(x, y);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (graphPanel.getGraphView() != null) {
            beginDragging(me);
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        selectedLinkView = null;
        selectedNodeView = null;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (graphPanel.getSelectedLinkViews().contains(selectedLinkView)
                && graphPanel.getSelectedLinkViews().size() == 1) {
            for (LinkView linkView : graphPanel.getSelectedLinkViews()) {
                linkView.setBendPoint(me.getX(), me.getY());
            }
            graphPanel.checkSize();
            graphPanel.repaint();
        } else if (graphPanel.getSelectedNodeViews().contains(selectedNodeView)) {
            Point pp = graphPanel.getGrid().getSnapToGridPoint(me.getX(), me.getY());
            int dx = (int) (pp.x - mousePosition.x);
            int dy = (int) (pp.y - mousePosition.y);
            for (LinkView linkView : graphPanel.getSelectedLinkViews()) {
                if (linkView.hasBendPoint()) {
                    Point bP = linkView.getBendPoint();
                    linkView.setBendPoint((int) bP.getX() + dx, (int) bP.getY() + dy);
                }
            }
            mousePosition.x += dx;
            mousePosition.y += dy;
            graphPanel.checkSize();
            graphPanel.repaint();
        }

    }

    @Override
    public void mouseMoved(MouseEvent me) {
        mousePosition = graphPanel.getGrid().getSnapToGridPoint(me.getX(), me.getY());
    }

    private void beginDragging(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        selectedNodeView = graphPanel.getGraphView().getNodeViewAtPoint(x, y);
        if (graphPanel.getSelectedNodeViews().contains(selectedNodeView)) {
            mousePosition = new Point(x, y);
        }
    }
}
