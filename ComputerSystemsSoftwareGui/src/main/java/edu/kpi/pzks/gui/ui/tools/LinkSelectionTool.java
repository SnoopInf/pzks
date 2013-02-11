package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.gui.modelview.LinkView;
import edu.kpi.pzks.gui.ui.GraphPanel;
import edu.kpi.pzks.gui.utils.COLORS;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author Aloren
 */
public class LinkSelectionTool implements SelectionDraggingTool {

    private final GraphPanel graphPanel;

    public LinkSelectionTool(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public void paint(Graphics2D g2) {
        paintSelectedLinkViews(g2);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        LinkView selectedLinkView = graphPanel.getGraphView().getLinkViewAtPoint(x, y);
        if (selectedLinkView != null) {
            setSelectedLinkView(selectedLinkView);
//            if (me.getModifiers() == MouseEvent.BUTTON3_MASK) {
//                JPopupMenu pp = selectedLinkView.getPopupMenu();
//                pp.show(me.getComponent(), me.getX(), me.getY());
//            }
        } else{
            clearSelected();
        }
        graphPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        LinkView linkViewAtPoint = graphPanel.getGraphView().getLinkViewAtPoint(x, y);
        if (!isLinkSelected(linkViewAtPoint)) {
            setSelectedLinkView(linkViewAtPoint);
        }
        graphPanel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

    private void setSelectedLinkView(LinkView selectedLinkView) {
        clearSelected();
        addToSelected(selectedLinkView);
    }

    private void clearSelected() {
        graphPanel.getSelectedLinkViews().clear();
    }

    private boolean isLinkSelected(LinkView linkViewAtPoint) {
        return !(linkViewAtPoint != null
                && !graphPanel.getSelectedLinkViews().contains(linkViewAtPoint));
    }

    private void addToSelected(LinkView linkViewAtPoint) {
        graphPanel.getSelectedLinkViews().add(linkViewAtPoint);
    }

    private void paintSelectedLinkViews(Graphics2D g2) {
        for (LinkView selectedLinkView : graphPanel.getSelectedLinkViews()) {
            paintSelectedLink(g2, selectedLinkView);
        }
    }

    private void paintSelectedLink(Graphics2D g2, LinkView selectedLinkView) {
        g2.setStroke(new BasicStroke(3));
        g2.setColor(COLORS.LINK_SELECTED_COLOR);
        selectedLinkView.paintWithoutColor(g2);
    }
}
