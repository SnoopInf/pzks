package edu.kpi.pzks.gui.actions.graph;

import edu.kpi.pzks.gui.ui.GraphPanel;
import edu.kpi.pzks.gui.ui.MainFrame;
import edu.kpi.pzks.gui.ui.tools.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Aloren
 */
public class SelectionDraggingToolAction extends AbstractAction {

    private final MainFrame mainFrame;

    public SelectionDraggingToolAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //TODO where to store these tools?
        setToolsForPanel(mainFrame.getTaskPanel());
        setToolsForPanel(mainFrame.getSystemPanel());
    }

    private void setToolsForPanel(GraphPanel taskPanel) {
        Set<Tool> taskPanelTools = getNewTools(taskPanel);
        taskPanel.setCurrentTools(taskPanelTools);
    }

    private Set<Tool> getNewTools(GraphPanel graphPanel) {
        Set<Tool> tools = new HashSet<>();
        RemoveKeyTool removeKeyTool = new RemoveKeyTool(graphPanel);
        //TODO this line is bad
        graphPanel.addKeyListener(removeKeyTool);
        tools.add(removeKeyTool);
        tools.add(new LinkSelectionTool(graphPanel));
        tools.add(new NodeSelectionTool(graphPanel));
        tools.add(new NodeDraggingTool(graphPanel));
        tools.add(new LinkDraggingTool(graphPanel));
        tools.add(new RectangleSelectionTool(graphPanel));
        return tools;
    }
}
