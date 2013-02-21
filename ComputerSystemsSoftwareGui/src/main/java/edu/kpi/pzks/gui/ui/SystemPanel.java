package edu.kpi.pzks.gui.ui;

import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.gui.modelview.impl.GraphViewImpl;

/**
 *
 * @author aloren
 */
public class SystemPanel extends GraphPanel {

    public SystemPanel() {
        super();
        this.graphView = new GraphViewImpl(new Graph(false));
    }

}
