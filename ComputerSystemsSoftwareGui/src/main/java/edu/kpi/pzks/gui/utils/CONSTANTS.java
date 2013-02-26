package edu.kpi.pzks.gui.utils;

import java.util.ResourceBundle;

public class CONSTANTS {

    public static final String iconsPath = "/icons";

    private static final ResourceBundle defaults = Resources.settings;

    public static final int NODE_WIDTH = Integer.parseInt(defaults.getString("node.width"));
    public static final int NODE_HEIGHT = Integer.parseInt(defaults.getString("node.height"));

    public static final String FONT_FAMILY = defaults.getString("font.family");
    public static final int FONT_SIZE = Integer.parseInt(defaults.getString("font.size"));
    public static final int FONT_WEIGHT = Integer.parseInt(defaults.getString("font.weight"));
    public static final String FONT_ID_FAMILY = defaults.getString("font.id.family");
    public static final int FONT_ID_SIZE = Integer.parseInt(defaults.getString("font.id.size"));
    public static final int FONT_ID_WEIGHT = Integer.parseInt(defaults.getString("font.id.weight"));

    public static final String YES_ICON = defaults.getString("yes.icon");
    public static final String NO_ICON = defaults.getString("no.icon");

    public static final int GRID_SPACING = Integer.parseInt(defaults.getString("grid.spacing"));
    public static final boolean GRID_SNAP = 
            Boolean.parseBoolean(defaults.containsKey("grid.snap") ? defaults.getString("grid.snap") : "true");
    public static final boolean GRID_ENABLED = 
            Boolean.parseBoolean(defaults.containsKey("grid.enabled") ? defaults.getString("grid.enabled") : "true");
}
