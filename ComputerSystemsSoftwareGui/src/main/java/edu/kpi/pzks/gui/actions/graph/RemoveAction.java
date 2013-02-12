package edu.kpi.pzks.gui.actions.graph;

import edu.kpi.pzks.gui.ui.MainFrame;
import edu.kpi.pzks.gui.ui.tools.RemoveTool;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * @author asmirnova
 */
public class RemoveAction extends AbstractAction {

    private MainFrame mainFrame;
    private final RemoveTool taskRemoveTool;
    private final RemoveTool systemRemoveTool;

    public RemoveAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.taskRemoveTool = new RemoveTool(mainFrame.getTaskPanel());
        this.systemRemoveTool = new RemoveTool(mainFrame.getSystemPanel());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.getTaskPanel().setCurrentTool(taskRemoveTool);
        mainFrame.getSystemPanel().setCurrentTool(systemRemoveTool);
    }
}
