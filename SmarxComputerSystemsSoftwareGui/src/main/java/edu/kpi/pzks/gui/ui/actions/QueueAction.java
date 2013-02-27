package edu.kpi.pzks.gui.ui.actions;

import edu.kpi.pzks.core.factory.GraphFactory;
import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.gui.layout.CircleLayout;
import edu.kpi.pzks.gui.modelview.GraphView;
import edu.kpi.pzks.gui.modelview.impl.GraphViewImpl;
import edu.kpi.pzks.gui.ui.MainFrame;
import edu.kpi.pzks.gui.ui.MainSmarxFrame;
import edu.kpi.pzks.gui.ui.panels.*;
import edu.kpi.pzks.gui.utils.STRINGS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

/**
 * @author smarx
 */
public class QueueAction extends AbstractAction{

    private MainFrame mainFrame;

    public QueueAction(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("QueueUIPanel");
        QueueUIPanel queueUIPanel = new QueueUIPanel();
        Graph graph = mainFrame.getTaskPanel().getGraphView().getGraph();
        queueUIPanel.setData(new QueueUIBean(graph.getNodes(), graph.getLinks()));
        frame.setContentPane(queueUIPanel.rootPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(mainFrame);
        frame.setTitle("Генерация очередей");
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
}
