package edu.kpi.pzks.gui.io;

import edu.kpi.pzks.core.exceptions.GraphException;
import edu.kpi.pzks.gui.modelview.GraphView;
import edu.kpi.pzks.gui.ui.GraphPanel.NodeType;
import java.io.File;

/**
 * Interface for graph loading.
 *
 * @author Aloren
 */
public interface GraphLoader {

    void setNodeType(NodeType type);

    GraphView loadFromFile(File file) throws GraphException;
}
