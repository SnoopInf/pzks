package edu.kpi.pzks.gui.utils;

import java.util.ResourceBundle;

public class CONSTANTS {

    private static final ResourceBundle defaults = Resources.settings;

    public static final int NODE_WIDTH = Integer.parseInt(defaults.getString("node.width"));
    public static final int NODE_HEIGHT = Integer.parseInt(defaults.getString("node.height"));

    public static final String FONT_FAMILY = defaults.getString("font.family");
    public static final int FONT_SIZE = Integer.parseInt(defaults.getString("font.size"));
    public static final int FONT_WEIGHT = Integer.parseInt(defaults.getString("font.weight"));

    public static final String YES_ICON = defaults.getString("yes.icon");
    public static final String NO_ICON = defaults.getString("no.icon");

    public static final int TOOLBAR_ORIENTATION = Integer.parseInt(defaults.getString("toolbar.orientation"));
    public static final String TOOLBAR_POSITION = defaults.getString("toolbar.position");

    public static final int GRID_SPACING = Integer.parseInt(defaults.getString("grid.spacing"));
}
