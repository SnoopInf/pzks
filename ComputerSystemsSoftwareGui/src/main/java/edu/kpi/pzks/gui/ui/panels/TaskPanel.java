package edu.kpi.pzks.gui.ui.panels;

import edu.kpi.pzks.gui.ui.panels.GraphPanel;
import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.gui.modelview.impl.GraphViewImpl;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 *
 * @author aloren
 */
public class TaskPanel extends GraphPanel {

    public TaskPanel() {
        super();
        this.graphView = new GraphViewImpl(new Graph(true));
        Dimension prefSize = this.getPreferredSize();
        final Rectangle bounds = new Rectangle(0, 0, (int) prefSize.getWidth(), (int) prefSize.getHeight());
        System.out.println("Bounds: "+bounds);
        this.graphView.setBounds(bounds);
    }
}
