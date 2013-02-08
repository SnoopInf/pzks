package edu.kpi.pzks.gui.ui;

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
    private JPanel systemPanel;
    private JPanel taskPanel;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(getMainMenuBar());
        add(getToolBar(TOOLBAR_ORIENTATION), BorderLayout.NORTH);
        this.taskPanel = getTaskPanel();
        this.systemPanel = getSystemPanel();
        setContentPane(getMainPane(taskPanel, systemPanel));
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
        JButton openButton = new JButton(openIcon);
        openButton.setToolTipText(resource.getString("open"));

        ImageIcon saveIcon = new ImageIcon(getClass().getResource(iconsPath + "/save.png"));
        JButton saveButton = new JButton(saveIcon);
        saveButton.setToolTipText(resource.getString("save"));

        ImageIcon taskIcon = new ImageIcon(getClass().getResource(iconsPath + "/task.png"));
        JButton genTaskGraphButton = new JButton(taskIcon);
        genTaskGraphButton.setToolTipText(resource.getString("generateTaskGraph"));

        ImageIcon systemIcon = new ImageIcon(getClass().getResource(iconsPath + "/system.png"));
        JButton genSystemGraphButton = new JButton(systemIcon);
        genSystemGraphButton.setToolTipText(resource.getString("generateSystemGraph"));

        toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.add(genTaskGraphButton);
        toolBar.add(genSystemGraphButton);
        return toolBar;
    }

    private Container getMainPane(JPanel taskPanel, JPanel systemPanel) {
        JSplitPane pane = new JSplitPane();
        pane.setDividerLocation(INIT_WIDTH / 2);
        pane.setLeftComponent(taskPanel);
        pane.setRightComponent(systemPanel);
        return pane;
    }

    private JPanel getTaskPanel() {
        JPanel taskPanel = new GraphPanel();
        taskPanel.setBorder(BorderFactory.createTitledBorder(resource.getString("taskGraph")));
        return taskPanel;

    }

    private JPanel getSystemPanel() {
        JPanel systemPanel = new GraphPanel();
        systemPanel.setBorder(BorderFactory.createTitledBorder(resource.getString("systemGraph")));
        return systemPanel;
    }
}
