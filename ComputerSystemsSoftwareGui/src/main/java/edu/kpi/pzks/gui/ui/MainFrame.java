package edu.kpi.pzks.gui.ui;

import edu.kpi.pzks.gui.actions.graph.LinkCreationToolAction;
import edu.kpi.pzks.gui.actions.graph.NodeCreationToolAction;
import edu.kpi.pzks.gui.actions.graph.SelectionDraggingToolAction;
import edu.kpi.pzks.gui.actions.ui.OpenAction;
import edu.kpi.pzks.gui.actions.ui.SaveAsAction;
import edu.kpi.pzks.gui.utils.CONSTANTS;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Aloren
 */
public class MainFrame extends JFrame {

    public static final int INIT_HEIGHT = 600;
    public static final int INIT_WIDTH = 800;
    public static final Locale locale = Locale.getDefault();//Locale.forLanguageTag("ru")
    private final int TOOLBAR_ORIENTATION = JToolBar.HORIZONTAL;
    private final String iconsPath = "/icons";
    protected ResourceBundle resource = ResourceBundle.getBundle("Menu", locale);
    protected GraphPanel systemPanel;
    protected GraphPanel taskPanel;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainFrame frame = new MainFrame(CONSTANTS.MAIN_TITLE); //TODO Move under resources
        frame.setVisible(true);
    }

    public MainFrame(String title) {
        super(title);
        this.taskPanel = createTaskPanel();
        this.systemPanel = createSystemPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(getMainMenuBar());
        add(getToolBar(TOOLBAR_ORIENTATION), BorderLayout.NORTH);
        add(getMainPane(taskPanel, systemPanel), BorderLayout.CENTER);
        setSize(INIT_WIDTH, INIT_HEIGHT);
        setLocationRelativeTo(null);
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
        JMenu modelingMenu = new JMenu(resource.getString("modelingMenu"));
        return modelingMenu;
    }

    protected JMenu getStatisticMenu() {
        JMenu statisticMenu = new JMenu(resource.getString("statisticMenu"));
        return statisticMenu;
    }

    protected JMenu getHelpMenu() {
        JMenu helpMenu = new JMenu(resource.getString("helpMenu"));
        return helpMenu;
    }

    protected JMenu getFileMenu() {
        JMenu fileMenu = new JMenu(resource.getString("fileMenu"));
        JMenuItem openMenuItem = new JMenuItem(resource.getString("open"));
        JMenuItem saveMenuItem = new JMenuItem(resource.getString("save"));
        JMenuItem exitMenuItem = new JMenuItem(resource.getString("exit"));
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }

    protected JToolBar getToolBar(int orientation) {
        JToolBar toolBar = new JToolBar(orientation);
        toolBar.setFloatable(false);

        ImageIcon openIcon = new ImageIcon(getClass().getResource(iconsPath + "/open.png"));
        JButton openButton = new JButton(new OpenAction(this));
        openButton.setIcon(openIcon);
        openButton.setToolTipText(resource.getString("open"));

        ImageIcon saveIcon = new ImageIcon(getClass().getResource(iconsPath + "/save.png"));
        JButton saveButton = new JButton(new SaveAsAction(this));
        saveButton.setIcon(saveIcon);
        saveButton.setToolTipText(resource.getString("save"));

        ImageIcon taskIcon = new ImageIcon(getClass().getResource(iconsPath + "/task.png"));
        JButton genTaskGraphButton = new JButton(taskIcon);
        genTaskGraphButton.setToolTipText(resource.getString("generateTaskGraph"));

        ImageIcon systemIcon = new ImageIcon(getClass().getResource(iconsPath + "/system.png"));
        JButton genSystemGraphButton = new JButton(systemIcon);
        genSystemGraphButton.setToolTipText(resource.getString("generateSystemGraph"));

        ImageIcon nodeIcon = new ImageIcon(getClass().getResource(iconsPath + "/node.png"));
        JButton newNodeButton = new JButton(new NodeCreationToolAction(this));
        newNodeButton.setIcon(nodeIcon);

        ImageIcon linkIcon = new ImageIcon(getClass().getResource(iconsPath + "/link.png"));
        JButton newLinkButton = new JButton(new LinkCreationToolAction(this));
        newLinkButton.setIcon(linkIcon);

        ImageIcon selectIcon = new ImageIcon(getClass().getResource(iconsPath + "/select.png"));
        JButton selectButton = new JButton(new SelectionDraggingToolAction(this));
        selectButton.setIcon(selectIcon);

        toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.add(genTaskGraphButton);
        toolBar.add(genSystemGraphButton);
        toolBar.add(newNodeButton);
        toolBar.add(newLinkButton);
        toolBar.add(selectButton);

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
        GraphPanel localTaskPanel = new GraphPanel();
        localTaskPanel.setName("taskPanel");
        localTaskPanel.setBorder(BorderFactory.createTitledBorder(resource.getString("taskGraph")));
        return localTaskPanel;

    }

    protected GraphPanel createSystemPanel() {
        GraphPanel localSystemPanel = new GraphPanel();
        localSystemPanel.setName("systemPanel");
        localSystemPanel.setBorder(BorderFactory.createTitledBorder(resource.getString("systemGraph")));
        return localSystemPanel;
    }

    public GraphPanel getSystemPanel() {
        return systemPanel;
    }

    public GraphPanel getTaskPanel() {
        return taskPanel;
    }
}
