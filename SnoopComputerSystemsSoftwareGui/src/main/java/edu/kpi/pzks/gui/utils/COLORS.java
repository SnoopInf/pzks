package edu.kpi.pzks.gui.utils;

import java.awt.*;
import java.util.ResourceBundle;

public class COLORS {

    private static final ResourceBundle colors = Resources.colors;

    public static final Color DEFAULT_COLOR = Color.decode(colors.getString("color.default"));

    public static final Color NODE_COLOR = Color.decode(colors.getString("color.node"));
    public static final Color NODE_SELECTED_COLOR = Color.decode(colors.getString("color.node.selected"));
    public static final Color NODE_BORDER_COLOR = Color.decode(colors.getString("color.node.border"));
    public static final Color NODE_BORDER_SELECTED_COLOR = Color.decode(colors.getString("color.node.border.selected"));

    public static final Color LINK_COLOR = Color.decode(colors.getString("color.link"));
    public static final Color LINK_SELECTED_COLOR = Color.decode(colors.getString("color.link.selected"));

    public static Color BLUE = Color.decode(colors.getString("color.blue"));
    public static Color GREY = Color.decode(colors.getString("color.grey"));
    public static Color FILL_BLUE = Color.decode(colors.getString("color.fill.blue"));

    public static final Color GRID_COLOR = Color.decode(colors.getString("color.grid"));
    Color test = new Color(0, 0, 255);

}
