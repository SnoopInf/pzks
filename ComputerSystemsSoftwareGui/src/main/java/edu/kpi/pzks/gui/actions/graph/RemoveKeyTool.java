package edu.kpi.pzks.gui.actions.graph;

import edu.kpi.pzks.gui.ui.GraphPanel;
import edu.kpi.pzks.gui.ui.tools.Tool;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

/**
 * @author asmirnova
 */
public class RemoveKeyTool implements Tool, KeyListener {

    private final GraphPanel graphPanel;

    public RemoveKeyTool(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public void paint(Graphics2D g2) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.graphPanel.requestFocusInWindow();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.graphPanel.requestFocusInWindow();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
            graphPanel.removeSelectedLinkViews();
            graphPanel.removeSelectedNodeViews();
            graphPanel.checkSize();
            graphPanel.repaint();
        }
    }
}
