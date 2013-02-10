package edu.kpi.pzks.gui.actions.ui;

import edu.kpi.pzks.gui.ui.MainFrame;
import javax.swing.AbstractAction;

public abstract class MainAction extends AbstractAction {

    protected final MainFrame mainFrame;

    public MainAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}
