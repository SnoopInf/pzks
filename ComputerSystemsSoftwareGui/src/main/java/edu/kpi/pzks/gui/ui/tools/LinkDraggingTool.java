package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.gui.modelview.LinkView;
import edu.kpi.pzks.gui.ui.GraphPanel;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author Aloren
 */
public class LinkDraggingTool implements SelectionDraggingTool {

    private final GraphPanel graphPanel;
//    private Point mousePosition = new Point(0, 0);
    private LinkView selectedLinkView;

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
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        selectedLinkView = null;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        //TODO update drag bendPoint for multiple links
        if (graphPanel.getSelectedLinkViews().contains(selectedLinkView)) {
            for (LinkView linkView : graphPanel.getSelectedLinkViews()) {
                linkView.setBendPoint(me.getX(), me.getY());
            }
            graphPanel.checkSize();
            graphPanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
//        mousePosition = graphPanel.getGrid().getSnapToGridPoint(me.getX(), me.getY());
    }
}
