package edu.kpi.pzks.gui.ui;

import edu.kpi.pzks.gui.actions.graph.LinkCreationToolAction;
import edu.kpi.pzks.gui.actions.graph.NodeCreationToolAction;
import edu.kpi.pzks.gui.actions.graph.RemoveAction;
import edu.kpi.pzks.gui.actions.graph.SelectionDraggingToolAction;
import edu.kpi.pzks.gui.utils.CONSTANTS;
import edu.kpi.pzks.gui.utils.STRINGS;
import edu.kpi.pzks.gui.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Kirill Davidenko
 */
public class CustomMainFrame extends MainFrame {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CustomMainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        CustomMainFrame frame = new CustomMainFrame(STRINGS.MAIN_TITLE);
        frame.setVisible(true);
    }

    @Override
    protected JMenu getFileMenu() {
        JMenu fileMenu = new JMenu(STRINGS.FILE_MENU);

        JMenuItem newMenuItem = new JMenuItem(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        newMenuItem.setText(STRINGS.NEW);

        JMenuItem openMenuItem = new JMenuItem(openAction);
        openMenuItem.setText(STRINGS.OPEN);

        JMenuItem saveAsMenuItem = new JMenuItem(saveAsAction);
        saveAsMenuItem.setText(STRINGS.SAVE);

        JMenuItem exitMenuItem = new JMenuItem(exitAction);
        exitMenuItem.setText(STRINGS.EXIT);
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }

    private void clear() {
        this.taskPanel.getGraphView().getGraph().removeAllValidators();
        this.taskPanel.getGraphView().getGraph().getLinks().clear();
        this.taskPanel.getGraphView().getGraph().getNodes().clear();
        this.taskPanel.getGraphView().getLinkViews().clear();
        this.taskPanel.getGraphView().getNodeViews().clear();

        this.systemPanel.getGraphView().getGraph().removeAllValidators();
        this.systemPanel.getGraphView().getGraph().getLinks().clear();
        this.systemPanel.getGraphView().getGraph().getNodes().clear();
        this.systemPanel.getGraphView().getLinkViews().clear();
        this.systemPanel.getGraphView().getNodeViews().clear();
    }

    @Override
    protected JToolBar getToolBar(int orientation) {
        JToolBar toolBar = new JToolBar(orientation);
        toolBar.setFloatable(false);

        ImageIcon openIcon = Utils.createImageIcon(iconsPath + "/open6.png");
        JButton openButton = new JButton(openAction);
        openButton.setIcon(openIcon);
        openButton.setToolTipText(STRINGS.OPEN);

        ImageIcon saveIcon = Utils.createImageIcon(iconsPath + "/save1.png");
        JButton saveButton = new JButton(saveAsAction);
        saveButton.setIcon(saveIcon);
        saveButton.setToolTipText(STRINGS.SAVE);

        ImageIcon taskIcon = Utils.createImageIcon(iconsPath + "/node5.png");
        JButton genTaskGraphButton = new JButton(taskIcon);
        genTaskGraphButton.setToolTipText(STRINGS.GEN_TASK_GRAPH);

        ImageIcon systemIcon = Utils.createImageIcon(iconsPath + "/node4.png");
        JButton genSystemGraphButton = new JButton(systemIcon);
        genSystemGraphButton.setToolTipText(STRINGS.GEN_SYSTEM_GRAPH);

        ImageIcon nodeIcon = Utils.createImageIcon(iconsPath + "/node6.png");
        JButton newNodeButton = new JButton(new NodeCreationToolAction(this));
        newNodeButton.setIcon(nodeIcon);

        ImageIcon linkIcon = Utils.createImageIcon(iconsPath + "/link5.png");
        JButton newLinkButton = new JButton(new LinkCreationToolAction(this));
        newLinkButton.setIcon(linkIcon);

        ImageIcon selectIcon = Utils.createImageIcon(iconsPath + "/select2.png");
        JButton selectButton = new JButton(new SelectionDraggingToolAction(this));
        selectButton.setIcon(selectIcon);

        ImageIcon removeIcon = Utils.createImageIcon(iconsPath + "/remove1.png");
        JButton removeButton = new JButton(new RemoveAction(this));
        removeButton.setIcon(removeIcon);

        toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.addSeparator();
        toolBar.add(newNodeButton);
        toolBar.add(newLinkButton);
        toolBar.add(selectButton);
        toolBar.add(removeButton);
        toolBar.addSeparator();
        toolBar.add(genTaskGraphButton);
        toolBar.add(genSystemGraphButton);
        toolBar.addSeparator();

        return toolBar;
    }

    public CustomMainFrame(String title) {
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(getMainMenuBar());


        this.taskPanel = getTaskPanel();
        this.systemPanel = getSystemPanel();

        setContentPane(getContentPanel());
        setSize(INIT_WIDTH, INIT_HEIGHT);
        setLocationRelativeTo(null);
    }

    protected Container getContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel toolBarPanel = new JPanel();
        toolBarPanel.setLayout(new BoxLayout(toolBarPanel, BoxLayout.PAGE_AXIS));

        toolBarPanel.add(new JPanel(), 1f);
        toolBarPanel.add(getToolBar(CONSTANTS.TOOLBAR_ORIENTATION), BorderLayout.EAST);
        toolBarPanel.add(new JPanel(), 1f);
        toolBarPanel.setOpaque(true);
        contentPanel.add(toolBarPanel, CONSTANTS.TOOLBAR_POSITION);
        contentPanel.add(getMainPane(taskPanel, systemPanel), BorderLayout.CENTER);
        return contentPanel;
    }
}
