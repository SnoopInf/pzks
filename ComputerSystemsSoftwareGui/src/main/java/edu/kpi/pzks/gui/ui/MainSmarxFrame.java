package edu.kpi.pzks.gui.ui;

import com.sun.java.swing.plaf.windows.WindowsTabbedPaneUI;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.*;

/**
 *
 * @author Andrew
 */
public class MainSmarxFrame extends MainFrame {

    public MainSmarxFrame(String title) {
        super(title, false);
        TOOLBAR_PLACEMENT = BorderLayout.WEST;
        TOOLBAR_ORIENTATION = JToolBar.VERTICAL;
        initFrame();
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