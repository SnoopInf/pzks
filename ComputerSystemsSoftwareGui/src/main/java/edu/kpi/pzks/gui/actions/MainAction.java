package edu.kpi.pzks.gui.actions;

import edu.kpi.pzks.gui.ui.GraphPanel;
import javax.swing.AbstractAction;

/**
 *
 * @author Aloren
 */
public abstract class MainAction extends AbstractAction {

    protected final GraphPanel graphPanel;

    public MainAction(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }
}
