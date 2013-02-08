package edu.kpi.pzks.gui.ui.utils;

import edu.kpi.pzks.gui.utils.CONSTANTS;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Aloren
 */
public class Grid {

    public static final int STANDART_SPACING = 5;
    private int spacing;
    private boolean snapToGrid = true;
    private boolean showGrid = true;

    public Grid() {
        this(true, true, STANDART_SPACING);
    }

    public Grid(boolean snapToGrid, boolean showGrid) {
        this(snapToGrid, showGrid, STANDART_SPACING);
    }

    public Grid(boolean snapToGrid, boolean showGrid, int spacing) {
        this.snapToGrid = snapToGrid;
        this.showGrid = showGrid;
        this.spacing = spacing;
    }

    public void paint(Graphics2D g, int width, int height) {
        if (showGrid) {
            g.setColor(CONSTANTS.GRID_COLOR);
            paintVerticalLines(width, g, height);
            paintHorizontalLines(height, g, width);
        }
    }

    public Point2D.Double getSnapToGridPoint(int x, int y) {
        int xGrid = x / spacing;
        int yGrid = y / spacing;
        if (snapToGrid) {
            return new Point2D.Double((int) xGrid * spacing, (int) yGrid * spacing);
        } else {
            return new Point2D.Double(x, y);
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
