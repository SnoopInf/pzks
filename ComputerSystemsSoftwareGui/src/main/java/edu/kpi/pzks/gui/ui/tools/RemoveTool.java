package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.gui.modelview.LinkView;
import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.ui.GraphPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

/**
 * @author Aloren
 */
public class RemoveTool implements Tool, KeyListener {

    private final GraphPanel graphPanel;

    public RemoveTool(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public void paint(Graphics2D g2) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        NodeView selectedNodeView = graphPanel.getGraphView().getNodeViewAtPoint(x, y);
        if (selectedNodeView != null) {
            graphPanel.getGraphView().removeNodeView(selectedNodeView);
            graphPanel.checkGraphIsValid();
            graphPanel.repaint();
        } else {
            LinkView selectedLinkView = graphPanel.getGraphView().getLinkViewAtPoint(x, y);
            if (selectedLinkView != null) {
                graphPanel.getGraphView().removeLinkView(selectedLinkView);
                graphPanel.checkGraphIsValid();
                graphPanel.repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
