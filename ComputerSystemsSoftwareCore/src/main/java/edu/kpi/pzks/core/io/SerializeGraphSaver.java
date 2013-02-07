package edu.kpi.pzks.core.io;

import edu.kpi.pzks.core.exceptions.GraphException;
import edu.kpi.pzks.core.io.GraphSaver;
import edu.kpi.pzks.core.model.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerializeGraphSaver implements GraphSaver {

    private final Graph graphToSave;

    public SerializeGraphSaver(Graph graphToSave) {
        this.graphToSave = graphToSave;
    }

    //TODO throw specific GraphException, so that client can handle it appropriately
    @Override
    public void saveToFile(File file) throws GraphException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(graphToSave);
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SerializeGraphSaver.class.getName()).log(Level.SEVERE, null, ex);
            throw new GraphException(ex);
        } catch (IOException ex) {
            Logger.getLogger(SerializeGraphSaver.class.getName()).log(Level.SEVERE, null, ex);
            throw new GraphException(ex);
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(SerializeGraphSaver.class.getName()).log(Level.SEVERE, null, ex);
                throw new GraphException(ex);
            }
        }
    }
}
