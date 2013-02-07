package edu.kpi.pzks.core.io;

import edu.kpi.pzks.core.exceptions.GraphException;
import edu.kpi.pzks.core.model.Graph;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aloren
 */
public class SerializeGraphLoader implements GraphLoader {

    //TODO throw specific GraphException, so that client can handle it appropriately
    @Override
    public Graph loadFromFile(File file) throws GraphException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            return (Graph) ois.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SerializeGraphLoader.class.getName()).log(Level.SEVERE, null, ex);
            throw new GraphException(ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SerializeGraphLoader.class.getName()).log(Level.SEVERE, null, ex);
            throw new GraphException(ex);
        } catch (IOException ex) {
            Logger.getLogger(SerializeGraphLoader.class.getName()).log(Level.SEVERE, null, ex);
            throw new GraphException(ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(SerializeGraphLoader.class.getName()).log(Level.SEVERE, null, ex);
                throw new GraphException(ex);
            }
        }
    }
}
