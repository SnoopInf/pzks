package edu.kpi.pzks.gui.io.impl;

import edu.kpi.pzks.core.exceptions.GraphException;
import edu.kpi.pzks.gui.io.GraphLoader;
import edu.kpi.pzks.gui.modelview.GraphView;
import java.io.File;

/**
 *
 * @author Aloren
 */
public class XmlGraphLoader implements GraphLoader {

    //TODO throw specific GraphException, so that client can handle it appropriately
    @Override
    public GraphView loadFromFile(File file) throws GraphException {
        throw new UnsupportedOperationException();
    }
}
