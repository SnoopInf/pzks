package edu.kpi.pzks.gui.actions.graph;

import edu.kpi.pzks.gui.ui.MainFrame;
import edu.kpi.pzks.gui.ui.tools.NodeCreationTool;
import edu.kpi.pzks.gui.ui.tools.Tool;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Aloren
 */
public class NodeCreationToolAction extends AbstractAction {

    private MainFrame mainFrame;
    private final Tool taskNodeCreationTool;
    private final Tool systemNodeCreationTool;

    public NodeCreationToolAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.taskNodeCreationTool = new NodeCreationTool(mainFrame.getTaskPanel());
        this.systemNodeCreationTool = new NodeCreationTool(mainFrame.getSystemPanel());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.getTaskPanel().setCurrentTool(taskNodeCreationTool);
        mainFrame.getSystemPanel().setCurrentTool(systemNodeCreationTool);
    }
}
