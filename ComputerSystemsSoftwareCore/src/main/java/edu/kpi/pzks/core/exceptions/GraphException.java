package edu.kpi.pzks.core.exceptions;

/**
 *
 * @author Aloren
 */
public class GraphException extends Exception {
    
    public GraphException(String message) {
        super(message);
    }

    public GraphException(Throwable ex) {
        super(ex);
    }
    
    public GraphException(String message, Throwable ex) {
        super(message, ex);
    }
    
}
