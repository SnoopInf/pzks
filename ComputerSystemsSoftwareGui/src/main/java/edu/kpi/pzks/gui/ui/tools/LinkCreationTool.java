package edu.kpi.pzks.gui.ui.tools;

import edu.kpi.pzks.gui.ui.GraphPanel;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author Aloren
 */
public class LinkCreationTool implements Tool {

    private final GraphPanel graphPanel;

    public LinkCreationTool(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public void paint(Graphics2D g2) {
//        System.out.println("PAINT");
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("Mouse clicked");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
