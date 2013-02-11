package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.gui.ui.GraphPanel;
import edu.kpi.pzks.gui.ui.MainFrame;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author Aloren
 */
public class RemoveTool implements Tool {

    private final GraphPanel graphPanel;
    private final MainFrame mainFrame;

    public RemoveTool(MainFrame mainFrame, GraphPanel graphPanel) {
        this.mainFrame = mainFrame;
        this.graphPanel = graphPanel;
    }

    @Override
    public void paint(Graphics2D g2) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
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
}
