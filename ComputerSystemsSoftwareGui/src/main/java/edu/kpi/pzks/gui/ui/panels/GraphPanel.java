package edu.kpi.pzks.gui.ui.panels;

import edu.kpi.pzks.core.exceptions.ValidationException;
import edu.kpi.pzks.core.validator.Validator;
import edu.kpi.pzks.gui.modelview.GraphView;
import edu.kpi.pzks.gui.modelview.LinkView;
import edu.kpi.pzks.gui.modelview.NodeView;
import edu.kpi.pzks.gui.ui.tools.Tool;
import edu.kpi.pzks.gui.ui.utils.Grid;
import edu.kpi.pzks.gui.utils.CONSTANTS;
import edu.kpi.pzks.gui.utils.Utils;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

/**
 * @author Aloren
 */
public abstract class GraphPanel extends JPanel {

    public static final int INIT_HEIGHT = 400;
    public static final int INIT_WIDTH = 800;
    protected JLabel validationLabel;
    protected Set<Tool> currentTools = new HashSet<>();
    private Grid grid = new Grid(CONSTANTS.GRID_SNAP, CONSTANTS.GRID_ENABLED);
    protected GraphView graphView;
    protected Set<NodeView> selectedNodeViews = new HashSet<>();
    protected Set<LinkView> selectedLinkViews = new HashSet<>();
    private static final ImageIcon valid = Utils.createImageIcon(CONSTANTS.iconsPath + CONSTANTS.YES_ICON);
    private static final ImageIcon invalid = Utils.createImageIcon(CONSTANTS.iconsPath + CONSTANTS.NO_ICON);

    public GraphPanel() {
        super(new BorderLayout());
        setPreferredSize(new Dimension(INIT_WIDTH / 2, INIT_HEIGHT));
        setBackground(Color.WHITE);
        setFocusable(true);
        createValidationLabel();
    }

    protected void createValidationLabel() {
        validationLabel = new JLabel(valid);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(validationLabel, BorderLayout.LINE_END);
        bottomPanel.setOpaque(false);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    public void setValid(boolean valid, String message) {
        if (valid) {
            validationLabel.setIcon(this.valid);
            validationLabel.setToolTipText("");
        } else {
            validationLabel.setIcon(this.invalid);
            validationLabel.setToolTipText(message);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        getGrid().paint(g2, getWidth(), getHeight());
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
        nodeView.setSelected(true);
    }

    public void clearSelectedNodeViews() {
        for (NodeView nodeView : selectedNodeViews) {
            nodeView.setSelected(false);
        }
        selectedNodeViews.clear();
    }

    public void clearSelectedLinkViews() {
        for (LinkView linkView : selectedLinkViews) {
            linkView.setSelected(false);
        }
        selectedLinkViews.clear();
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
            Point upperLeftCorner = nodeView.getUpperLeftCorner();
            if ((upperLeftCorner.x + nodeView.getWidth()) > dimension.width) {
                dimension.width = upperLeftCorner.x + nodeView.getWidth() + 10;
            }
            if ((upperLeftCorner.y + nodeView.getHeight()) > dimension.height) {
                dimension.height = upperLeftCorner.y + nodeView.getHeight() + 10;
            }
            final boolean isXMinus = upperLeftCorner.x < 0;
            if (isXMinus || upperLeftCorner.y < 0) {
                if (isXMinus) {
                    upperLeftCorner.x = 0;
                } else {
                    upperLeftCorner.y = 0;
                }
                nodeView.setUpperLeftCorner(upperLeftCorner);
            }
        }
        graphView.setBounds(getBounds());
        this.setSize(dimension);
        this.setPreferredSize(dimension);
    }

    public Set<LinkView> getSelectedLinkViews() {
        return selectedLinkViews;
    }

    public void setSelectedLinkViews(Set<LinkView> selectedLinkViews) {
        this.selectedLinkViews = selectedLinkViews;
    }

    public void addSelectedLinkView(LinkView linkView) {
        this.selectedLinkViews.add(linkView);
    }

    public void removeSelectedLinkViews() {
        graphView.removeLinkViews(selectedLinkViews);
        selectedLinkViews.clear();
    }

    public void removeSelectedNodeViews() {
        graphView.removeNodeViews(selectedNodeViews);
        selectedNodeViews.clear();
    }

    public void checkGraphIsValid() {
        try {
            graphView.getGraph().validate();
            setValid(true, null);
        } catch (ValidationException e) {
            setValid(false, e.getMessage());
        }
    }

    public void addValidator(Validator validator) {
        graphView.getGraph().addValidator(validator);
    }
//    public NodeType getType() {
//        return type;
//    }
}
