package edu.kpi.pzks.gui.ui.utils;

import edu.kpi.pzks.gui.utils.COLORS;
import edu.kpi.pzks.gui.utils.CONSTANTS;

import java.awt.*;

/**
 * @author Aloren
 */
public class Grid {

    private int spacing;
    private boolean snapToGrid = true;
    private boolean showGrid = true;

    public Grid() {
        this(true, true, CONSTANTS.GRID_SPACING);
    }

    public Grid(boolean snapToGrid, boolean showGrid) {
        this(snapToGrid, showGrid, CONSTANTS.GRID_SPACING);
    }

    public Grid(boolean snapToGrid, boolean showGrid, int spacing) {
        this.snapToGrid = snapToGrid;
        this.showGrid = showGrid;
        this.spacing = spacing;
    }

    public void paint(Graphics2D g, int width, int height) {
        if (showGrid) {
            g.setColor(COLORS.GRID_COLOR);
            paintVerticalLines(width, g, height);
            paintHorizontalLines(height, g, width);
        }
    }

    public Point getSnapToGridPoint(int x, int y) {
        int xGrid = x / spacing;
        int yGrid = y / spacing;
        if (snapToGrid) {
            return new Point(xGrid * spacing, yGrid * spacing);
        } else {
            return new Point(x, y);
        }
    }

    public boolean isSnapToGrid() {
        return snapToGrid;
    }

    public void setSnapToGrid(boolean snapToGrid) {
        this.snapToGrid = snapToGrid;
    }

    public boolean isShowGrid() {
        return showGrid;
    }

    public void setShowGrid(boolean showGrid) {
        this.showGrid = showGrid;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

    public int getSpacing() {
        return this.spacing;
    }

    private void paintVerticalLines(int width, Graphics2D g, int height) {
        for (int i = spacing; i < width; i += spacing) {
            g.drawLine(i, 0, i, height);
        }
    }

    private void paintHorizontalLines(int height, Graphics2D g, int width) {
        for (int i = spacing; i < height; i += spacing) {
            g.drawLine(0, i, width, i);
        }
    }
}
