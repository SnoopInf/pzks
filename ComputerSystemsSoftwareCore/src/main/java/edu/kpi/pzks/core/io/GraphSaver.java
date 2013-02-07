package edu.kpi.pzks.core.io;

import edu.kpi.pzks.core.exceptions.GraphException;
import java.io.File;

/**
 * Interface for graph saving.
 * @author Aloren
 */
public interface GraphSaver {
    
    void saveToFile(File file) throws GraphException;
    
}
