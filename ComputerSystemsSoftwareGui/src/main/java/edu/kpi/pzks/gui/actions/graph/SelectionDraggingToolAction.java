package edu.kpi.pzks.gui.actions.graph;

import edu.kpi.pzks.gui.ui.GraphPanel;
import edu.kpi.pzks.gui.ui.MainFrame;
import edu.kpi.pzks.gui.ui.tools.LinkDraggingTool;
import edu.kpi.pzks.gui.ui.tools.LinkSelectionTool;
import edu.kpi.pzks.gui.ui.tools.NodeDraggingTool;
import edu.kpi.pzks.gui.ui.tools.NodeSelectionTool;
import edu.kpi.pzks.gui.ui.tools.Tool;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.AbstractAction;

/**
 *
 * @author Aloren
 */
public class SelectionDraggingToolAction extends AbstractAction {

    private final MainFrame mainFrame;

    public SelectionDraggingToolAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        setToolsForPanel(mainFrame.getTaskPanel());
        setToolsForPanel(mainFrame.getSystemPanel());
    }

    private void setToolsForPanel(GraphPanel taskPanel) {
        Set<Tool> taskPanelTools = getNewTools(taskPanel);
        taskPanel.setCurrentTools(taskPanelTools);
    }

    private Set<Tool> getNewTools(GraphPanel graphPanel) {
        //TODO where to store this tools???
        //when we press button some class must already know abpit them
        //but not instantiate!
        Set<Tool> tools = new HashSet<>();
        tools.add(new LinkSelectionTool(graphPanel));
        tools.add(new NodeSelectionTool(graphPanel));
        tools.add(new NodeDraggingTool(graphPanel));
        tools.add(new LinkDraggingTool(graphPanel));
        return tools;
    }
}
