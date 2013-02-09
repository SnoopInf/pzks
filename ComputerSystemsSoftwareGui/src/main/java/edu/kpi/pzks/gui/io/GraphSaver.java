package edu.kpi.pzks.gui.io;

import edu.kpi.pzks.core.exceptions.GraphException;
import edu.kpi.pzks.gui.modelview.GraphView;
import java.io.File;

/**
 * Interface for graph saving.
 * @author Aloren
 */
public interface GraphSaver {

    void saveToFile(GraphView graphView, File file) throws GraphException;

}
