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
public class SystemPanel extends GraphPanel {

    public SystemPanel() {
        super();
        this.graphView = new GraphViewImpl(new Graph(false));
        Dimension prefSize = this.getPreferredSize();
        this.graphView.setBounds(new Rectangle(0, 0, (int) prefSize.getWidth(), (int) prefSize.getHeight()));
    }

}
