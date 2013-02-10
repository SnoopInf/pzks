package edu.kpi.pzks.gui.ui;

import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.gui.modelview.GraphView;
import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.modelview.impl.GraphViewImpl;
import edu.kpi.pzks.gui.ui.tools.Tool;
import edu.kpi.pzks.gui.ui.utils.Grid;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;

/**
 *
 * @author Aloren
 */
public class GraphPanel extends JPanel {

    private Set<Tool> currentTools = new HashSet<>();
    private Grid grid = new Grid();
    private GraphView graphView = new GraphViewImpl(new Graph());
    private Set<NodeView> selectedNodeViews = new HashSet<>();

    public GraphPanel() {
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        grid.paint(g2, getWidth(), getHeight());
        graphView.paint(g2);
        for (Tool selectionDraggingTool : currentTools) {
            selectionDraggingTool.paint(g2);
        }
    }

    public GraphView getGraphView() {
        return this.graphView;
    }

    public void setGraphView(GraphView graphView) {
        this.graphView = graphView;
    }

    public void setCurrentTool(Tool tool) {
        clearAllTools();
        currentTools.add(tool);
        addToolAsMouseListener(tool);
    }

    public void setCurrentTools(Set<Tool> tools) {
        clearAllTools();
        currentTools.addAll(tools);
        for (Tool tool : tools) {
            addToolAsMouseListener(tool);
        }
    }

    public Grid getGrid() {
        return this.grid;
    }

    private void addToolAsMouseListener(Tool tool) {
        this.addMouseListener(tool);
        this.addMouseMotionListener(tool);
    }

    private void clearAllTools() {
        for (Tool tool : currentTools) {
            this.removeMouseListener(tool);
            this.removeMouseMotionListener(tool);
        }
        this.currentTools.clear();
    }

    public void addSelectedNodeView(NodeView nodeView) {
        this.selectedNodeViews.add(nodeView);
    }

    public Set<NodeView> getSelectedNodeViews() {
        return selectedNodeViews;
    }

    public void setSelectedNodeViews(Set<NodeView> selectedNodeViews) {
        this.selectedNodeViews = selectedNodeViews;
    }

    public void checkSize() {
        Dimension dimension = new Dimension(0, 0);
        for (NodeView nodeView : graphView.getNodeViews()) {
            Point point = nodeView.getUpperLeftCorner();
            if ((point.x + nodeView.getWidth()) > dimension.width) {
                dimension.width = point.x + nodeView.getWidth() + 10;
            }
            if ((point.y + nodeView.getHeight()) > dimension.height) {
                dimension.height = point.y + nodeView.getHeight() + 10;
            }
        }
        this.setSize(dimension);
        this.setPreferredSize(dimension);
    }
}
