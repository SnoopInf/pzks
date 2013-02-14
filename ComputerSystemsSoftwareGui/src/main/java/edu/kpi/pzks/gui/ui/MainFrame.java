package edu.kpi.pzks.gui.ui;

import edu.kpi.pzks.core.validator.ConsistencyValidator;
import edu.kpi.pzks.core.validator.CyclingValidator;
import edu.kpi.pzks.gui.actions.graph.LinkCreationToolAction;
import edu.kpi.pzks.gui.actions.graph.NodeCreationToolAction;
import edu.kpi.pzks.gui.actions.graph.RemoveAction;
import edu.kpi.pzks.gui.actions.graph.SelectionDraggingToolAction;
import edu.kpi.pzks.gui.actions.ui.ExitAction;
import edu.kpi.pzks.gui.actions.ui.OpenAction;
import edu.kpi.pzks.gui.actions.ui.SaveAsAction;
import edu.kpi.pzks.gui.utils.STRINGS;
import edu.kpi.pzks.gui.utils.Utils;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Aloren
 */
public class MainFrame extends JFrame {

    public static final int INIT_HEIGHT = 600;
    public static final int INIT_WIDTH = 800;
    private final String iconsPath = "/icons";
    protected GraphPanel systemPanel;
    protected GraphPanel taskPanel;
    protected final OpenAction openAction = new OpenAction(this);
    protected final SaveAsAction saveAsAction = new SaveAsAction(this);
    protected final ExitAction exitAction = new ExitAction(this);

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainFrame frame = new MainFrame(STRINGS.MAIN_TITLE);
        frame.setVisible(true);
    }

    public MainFrame(String title) {
        super(title);
        setComponents();
        setSizeAndPosition();
    }

    protected JMenuBar getMainMenuBar() {
        JMenuBar bar = new JMenuBar();
        bar.add(getFileMenu());
        bar.add(getModelingMenu());
        bar.add(getStatisticMenu());
        bar.add(getHelpMenu());
        return bar;
    }

    protected JMenu getModelingMenu() {
        JMenu modelingMenu = new JMenu(STRINGS.MODELING_MENU);
        return modelingMenu;
    }

    protected JMenu getStatisticMenu() {
        JMenu statisticMenu = new JMenu(STRINGS.STATISTIC_MENU);
        return statisticMenu;
    }

    protected JMenu getHelpMenu() {
        JMenu helpMenu = new JMenu(STRINGS.HELP_MENU);
        return helpMenu;
    }

    protected JMenu getFileMenu() {
        JMenu fileMenu = new JMenu(STRINGS.FILE_MENU);

        JMenuItem openMenuItem = new JMenuItem(openAction);
        openMenuItem.setText(STRINGS.OPEN);

        JMenuItem saveAsMenuItem = new JMenuItem(saveAsAction);
        saveAsMenuItem.setText(STRINGS.SAVE);

        JMenuItem exitMenuItem = new JMenuItem(exitAction);
        exitMenuItem.setText(STRINGS.EXIT);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(exitMenuItem);
        return fileMenu;
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

    protected Container getMainPane(JPanel taskPanel, JPanel systemPanel) {
        JSplitPane pane = new JSplitPane();
        pane.setDividerLocation(INIT_WIDTH / 2);

        JScrollPane taskPane = new JScrollPane();
        taskPane.setViewportView(taskPanel);
        pane.setLeftComponent(taskPane);

        JScrollPane systemPane = new JScrollPane();
        systemPane.setViewportView(systemPanel);
        pane.setRightComponent(systemPane);
        return pane;
    }

    protected GraphPanel createTaskPanel() {
        GraphPanel localTaskPanel = new GraphPanel(GraphPanel.NodeType.Task);
        localTaskPanel.setName("taskPanel");
        localTaskPanel.setBorder(BorderFactory.createTitledBorder(STRINGS.TASK_GRAPH));
        localTaskPanel.addValidator(new CyclingValidator());
        return localTaskPanel;

    }

    protected GraphPanel createSystemPanel() {
        GraphPanel localSystemPanel = new GraphPanel(GraphPanel.NodeType.System);
        localSystemPanel.setName("systemPanel");
        localSystemPanel.setBorder(BorderFactory.createTitledBorder(STRINGS.SYSTEM_GRAPH));
        localSystemPanel.addValidator(new ConsistencyValidator());
        return localSystemPanel;
    }

    public GraphPanel getSystemPanel() {
        return systemPanel;
    }

    public GraphPanel getTaskPanel() {
        return taskPanel;
    }

    protected void setComponents() {
        this.taskPanel = createTaskPanel();
        this.systemPanel = createSystemPanel();
        setJMenuBar(getMainMenuBar());
        add(getToolBar(getToolbarOrientation()), getToolbarConstraint());
        add(getMainPane(taskPanel, systemPanel), BorderLayout.CENTER);
    }

    protected void setSizeAndPosition() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(INIT_WIDTH, INIT_HEIGHT);
        setLocationRelativeTo(null);
    }

    protected int getToolbarOrientation() {
        return JToolBar.HORIZONTAL;
    }

    protected String getToolbarConstraint() {
        return BorderLayout.NORTH;
    }
}
