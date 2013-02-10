package edu.kpi.pzks.gui.ui;

import edu.kpi.pzks.gui.actions.graph.LinkCreationToolAction;
import edu.kpi.pzks.gui.actions.graph.NodeCreationToolAction;
import edu.kpi.pzks.gui.actions.graph.SelectionDraggingToolAction;
import edu.kpi.pzks.gui.actions.ui.OpenAction;
import edu.kpi.pzks.gui.actions.ui.SaveAsAction;
import edu.kpi.pzks.gui.utils.CONSTANTS;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Locale;
import java.util.ResourceBundle;
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
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Aloren
 */
public class MainFrame extends JFrame {

    public static final int INIT_HEIGHT = 600;
    public static final int INIT_WIDTH = 800;
    public static final Locale locale = Locale.getDefault();//Locale.forLanguageTag("ru")
    private final int TOOLBAR_ORIENTATION = JToolBar.HORIZONTAL;
    private final String iconsPath = "/icons";
    private ResourceBundle resource = ResourceBundle.getBundle("Menu", locale);
    private GraphPanel systemPanel;
    private GraphPanel taskPanel;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainFrame frame = new MainFrame(CONSTANTS.MAIN_TITLE);
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

    private JMenuBar getMainMenuBar() {
        JMenuBar bar = new JMenuBar();
        bar.add(getFileMenu());
        bar.add(getModelingMenu());
        bar.add(getStatisticMenu());
        bar.add(getHelpMenu());
        return bar;
    }

    private JMenu getModelingMenu() {
        JMenu modelingMenu = new JMenu(resource.getString("modelingMenu"));
        return modelingMenu;
    }

    private JMenu getStatisticMenu() {
        JMenu statisticMenu = new JMenu(resource.getString("statisticMenu"));
        return statisticMenu;
    }

    private JMenu getHelpMenu() {
        JMenu helpMenu = new JMenu(resource.getString("helpMenu"));
        return helpMenu;
    }

    private JMenu getFileMenu() {
        JMenu fileMenu = new JMenu(resource.getString("fileMenu"));
        JMenuItem openMenuItem = new JMenuItem(resource.getString("open"));
        JMenuItem saveMenuItem = new JMenuItem(resource.getString("save"));
        JMenuItem exitMenuItem = new JMenuItem(resource.getString("exit"));
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }

    private JToolBar getToolBar(int orientation) {
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

    private Container getMainPane(JPanel taskPanel, JPanel systemPanel) {
        JSplitPane pane = new JSplitPane();
        pane.setDividerLocation(INIT_WIDTH / 2);
        pane.setLeftComponent(taskPanel);
        pane.setRightComponent(systemPanel);
        return pane;
    }

    private GraphPanel createTaskPanel() {
        GraphPanel localTaskPanel = new GraphPanel();
        localTaskPanel.setName("taskPanel");
        localTaskPanel.setBorder(BorderFactory.createTitledBorder(resource.getString("taskGraph")));
        return localTaskPanel;

    }

    private GraphPanel createSystemPanel() {
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
