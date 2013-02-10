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
    private final GraphPanel graphPanel;

    public SelectionDraggingToolAction(MainFrame mainFrame, GraphPanel graphPanel) {
        this.mainFrame = mainFrame;
        this.graphPanel = graphPanel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //TODO where to store this tools???
        //when we press button some class must already know abpit them
        //but not instantiate!
        Set<Tool> tools = new HashSet<>();
        tools.add(new LinkSelectionTool(graphPanel));
        tools.add(new NodeSelectionTool(graphPanel));
        tools.add(new NodeDraggingTool(graphPanel));
        tools.add(new LinkDraggingTool(graphPanel));
        this.graphPanel.setCurrentTools(tools);
    }
}
