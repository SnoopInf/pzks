package edu.kpi.pzks.gui.layout;

import edu.kpi.pzks.gui.modelview.NodeView;

/**
 *
 * @author asmirnova
 */
public class LayoutUtils {

    static int getMaxNodeWidth(NodeView[] allNodes) {
        int maxWidth = 0;
        for (NodeView nodeView : allNodes) {
            int width = nodeView.getWidth();
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }

    static int getMaxNodeHeight(NodeView[] allNodes) {
        int maxHeight = 0;
        for (NodeView nodeView : allNodes) {
            int height = nodeView.getHeight();
            if (height > maxHeight) {
                maxHeight = height;
            }
        }
        return maxHeight;
    }
    
}
