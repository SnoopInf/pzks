package edu.kpi.pzks.gui.ui.panels;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.queue.Queue;
import edu.kpi.pzks.core.queue.QueuedNode;
import edu.kpi.pzks.core.queue.SingleFactorQueue;
import edu.kpi.pzks.core.queue.factors.WeightedCriticalFactorEvaluatorImpl;
import edu.kpi.pzks.gui.ui.MainFrame;
import edu.kpi.pzks.gui.utils.PaintUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author smarx
 */
public class QueueUIPanel {

    private JButton buttonQueue1;
    private JTextArea queueOutputPane;
    private JButton buttonQueue4;
    private JButton buttonQueue9;
    public JPanel rootPanel;
    private MainFrame mainFrame;
    private Set<Node> nodes;
    private Collection<Link> links;

    public QueueUIPanel() {
        buttonQueue1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Queue queueGenerator = new SingleFactorQueue(new WeightedCriticalFactorEvaluatorImpl(nodes, links), nodes);
                Collection<QueuedNode> nodes = queueGenerator.evaluate();
                for(QueuedNode queuedNode : nodes) {
                    queueOutputPane.append(queuedNode.toString() + "\n");
                }
            }
        });
        queueOutputPane.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    }

    public void setData(QueueUIBean data) {
        nodes = data.getNodes();
        links = data.getLinks();
    }

    public void getData(QueueUIBean data) {
    }

    public boolean isModified(QueueUIBean data) {
        return false;
    }

}
