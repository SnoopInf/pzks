package edu.kpi.pzks.gui.ui;

import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.gui.modelview.GraphView;
import edu.kpi.pzks.gui.modelview.impl.GraphViewImpl;
import edu.kpi.pzks.gui.ui.utils.Grid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Aloren
 */
public class GraphPanel extends JPanel {

    private Grid grid = new Grid();
    private GraphView graphView = new GraphViewImpl(new Graph());

    public GraphPanel() {
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        grid.paint(g2, getWidth(), getHeight());
    }
}
