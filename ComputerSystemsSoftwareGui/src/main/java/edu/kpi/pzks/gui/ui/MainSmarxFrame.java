package edu.kpi.pzks.gui.ui;

import com.sun.java.swing.plaf.windows.WindowsTabbedPaneUI;
import edu.kpi.pzks.gui.utils.STRINGS;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Andrew
 */
public class MainSmarxFrame extends MainFrame {

    protected int TOOLBAR_ORIENTATION = JToolBar.VERTICAL;
    protected String TOOLBAR_CONSTRAINT = BorderLayout.WEST;
    
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
        pane.add("PROBLEM", taskPane);

        JScrollPane systemPane = new JScrollPane();
        systemPane.setViewportView(systemPanel);
        pane.add("SYSTEM", systemPane);
        return pane;
    }
}