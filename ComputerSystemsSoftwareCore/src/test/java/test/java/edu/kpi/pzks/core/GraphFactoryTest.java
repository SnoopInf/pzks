package test.java.edu.kpi.pzks.core;

import edu.kpi.pzks.core.factory.GraphFactory;
import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.core.validator.CyclingValidator;
import edu.kpi.pzks.core.validator.Validator;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Aloren
 */
public class GraphFactoryTest {
    
    @Test
    public void testFactory() {
        int numberOfNodes = 5;
        int minNodeWeight = 1, maxNodeWeight = 5;
        double connectivity = 0.5;
        
        Graph graph = GraphFactory.newGraph(numberOfNodes, minNodeWeight, maxNodeWeight, connectivity);
        Validator validator = new CyclingValidator();
        assertTrue(validator.validate(graph.getNodes(), graph.getLinks()));
        
    }
    
}
