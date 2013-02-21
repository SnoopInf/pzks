package edu.kpi.pzks.gui.ui;

import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.gui.modelview.impl.GraphViewImpl;

/**
 *
 * @author asmirnova
 */
public class TaskPanel extends GraphPanel {

    public TaskPanel() {
        super();
        this.graphView = new GraphViewImpl(new Graph(true));
    }
}
