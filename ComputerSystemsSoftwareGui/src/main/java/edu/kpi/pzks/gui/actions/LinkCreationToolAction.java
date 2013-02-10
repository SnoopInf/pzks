package edu.kpi.pzks.gui.actions;

import edu.kpi.pzks.gui.ui.MainFrame;
import edu.kpi.pzks.gui.ui.tools.LinkCreationTool;
import edu.kpi.pzks.gui.ui.tools.Tool;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Aloren
 */
public class LinkCreationToolAction extends AbstractAction {

    private MainFrame mainFrame;
    private final Tool taskLinkCreationTool;
    private final Tool systemLinkCreationTool;

    public LinkCreationToolAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.taskLinkCreationTool = new LinkCreationTool(mainFrame.getTaskPanel());
        this.systemLinkCreationTool = new LinkCreationTool(mainFrame.getSystemPanel());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.getTaskPanel().setCurrentTool(taskLinkCreationTool);
        mainFrame.getSystemPanel().setCurrentTool(systemLinkCreationTool);
    }
}
