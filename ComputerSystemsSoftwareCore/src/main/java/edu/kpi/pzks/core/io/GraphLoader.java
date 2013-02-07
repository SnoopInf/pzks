package edu.kpi.pzks.core.io;

import edu.kpi.pzks.core.exceptions.GraphException;
import edu.kpi.pzks.core.model.Graph;
import java.io.File;

/**
 * Interface for graph loading.
 * @author Aloren
 */
public interface GraphLoader {

    Graph loadFromFile(File file) throws GraphException;
}
