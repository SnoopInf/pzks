package edu.kpi.pzks.gui.ui;

import com.sun.java.swing.plaf.windows.WindowsTabbedPaneUI;
import edu.kpi.pzks.core.validator.ConsistencyValidator;
import edu.kpi.pzks.core.validator.CyclingValidator;
import edu.kpi.pzks.gui.actions.graph.LinkCreationToolAction;
import edu.kpi.pzks.gui.actions.graph.NodeCreationToolAction;
import edu.kpi.pzks.gui.actions.graph.RemoveAction;
import edu.kpi.pzks.gui.actions.graph.SelectionDraggingToolAction;
import edu.kpi.pzks.gui.utils.STRINGS;
import edu.kpi.pzks.gui.utils.Utils;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author smarx
 */
public class MainSmarxFrame extends MainFrame {
    
    private final String iconsPath = "/icons";
        
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainSmarxFrame frame = new MainSmarxFrame(STRINGS.MAIN_TITLE);
        frame.setVisible(true);
    }
    
    public MainSmarxFrame(String title) {
        super(title);
    }
    
    @Override
    protected Container getMainPane(JPanel taskPanel, JPanel systemPanel) {
        JTabbedPane pane = new JTabbedPane();
        pane.setUI(new WindowsTabbedPaneUI());

        JScrollPane taskPane = new JScrollPane();
        taskPane.setViewportView(taskPanel);
        pane.add(STRINGS.TASK_GRAPH, taskPane);

        JScrollPane systemPane = new JScrollPane();
        systemPane.setViewportView(systemPanel);
        pane.add(STRINGS.SYSTEM_GRAPH, systemPane);
        return pane;
    }
    
    protected GraphPanel createTaskPanel() {
        GraphPanel localTaskPanel = new TaskPanel();
        localTaskPanel.setName("taskPanel");
        localTaskPanel.addValidator(new CyclingValidator());
        return localTaskPanel;

    }

    protected GraphPanel createSystemPanel() {
        GraphPanel localSystemPanel = new SystemPanel();
        localSystemPanel.setName("systemPanel");
        localSystemPanel.addValidator(new ConsistencyValidator());
        return localSystemPanel;
    }
    
    protected JMenu getModelingMenu() {
        JMenu modelingMenu = new JMenu(STRINGS.MODELING_MENU);

        JMenuItem ganntMenuItem = new JMenuItem();
        ganntMenuItem.setText(STRINGS.GANTT);
        modelingMenu.add(ganntMenuItem);
        
        JMenuItem parameterMenuItem = new JMenuItem();
        parameterMenuItem.setText(STRINGS.CPU_PARAMETERS);
        modelingMenu.add(parameterMenuItem);
        
        
        return modelingMenu;
    }
    
    protected JToolBar getToolBar(int orientation) {
        JToolBar toolBar = new JToolBar(orientation);
        toolBar.setFloatable(false);

        ImageIcon openIcon = Utils.createImageIcon(iconsPath + "/open.png");
        JButton openButton = new JButton(openAction);
        openButton.setIcon(openIcon);
        openButton.setToolTipText(STRINGS.OPEN);

        ImageIcon saveIcon = Utils.createImageIcon(iconsPath + "/save.png");
        JButton saveButton = new JButton(saveAsAction);
        saveButton.setIcon(saveIcon);
        saveButton.setToolTipText(STRINGS.SAVE);

        ImageIcon taskIcon = Utils.createImageIcon(iconsPath + "/task.png");
        JButton genTaskGraphButton = new JButton(taskIcon);
        genTaskGraphButton.setToolTipText(STRINGS.GEN_TASK_GRAPH);

        ImageIcon systemIcon = Utils.createImageIcon(iconsPath + "/system.png");
        JButton genSystemGraphButton = new JButton(systemIcon);
        genSystemGraphButton.setToolTipText(STRINGS.GEN_SYSTEM_GRAPH);

        ImageIcon nodeIcon = Utils.createImageIcon(iconsPath + "/node.png");
        JButton newNodeButton = new JButton(new NodeCreationToolAction(this));
        newNodeButton.setIcon(nodeIcon);

        ImageIcon linkIcon = Utils.createImageIcon(iconsPath + "/link.png");
        JButton newLinkButton = new JButton(new LinkCreationToolAction(this));
        newLinkButton.setIcon(linkIcon);

        ImageIcon selectIcon = Utils.createImageIcon(iconsPath + "/select.png");
        JButton selectButton = new JButton(new SelectionDraggingToolAction(this));
        selectButton.setIcon(selectIcon);

        ImageIcon removeIcon = Utils.createImageIcon(iconsPath + "/remove.png");
        JButton removeButton = new JButton(new RemoveAction(this));
        removeButton.setIcon(removeIcon);

        
        toolBar.add(newNodeButton);
        toolBar.add(newLinkButton);
        toolBar.add(selectButton);
        toolBar.add(removeButton);
        toolBar.addSeparator();
        toolBar.add(genTaskGraphButton);
        toolBar.add(genSystemGraphButton);
        toolBar.addSeparator();
        toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.addSeparator();

        return toolBar;
    }
    
    @Override
    protected int getToolbarOrientation() {
        return JToolBar.VERTICAL;
    }

    @Override
    protected String getToolbarConstraint() {
        return BorderLayout.WEST;
    }
}