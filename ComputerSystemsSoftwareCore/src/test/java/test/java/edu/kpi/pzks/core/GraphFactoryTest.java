package test.java.edu.kpi.pzks.core;

import edu.kpi.pzks.core.factory.GraphFactory;
import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.validator.CyclingValidator;
import edu.kpi.pzks.core.validator.Validator;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author Aloren
 */
@RunWith(value = Parameterized.class)
public class GraphFactoryTest {

    private final int numberOfNodes;
    private final int minNodeWeight;
    private final int maxNodeWeight;
    private final double connectivity;

    public GraphFactoryTest(int numberOfNodes, int minNodeWeight, int maxNodeWeight, double connectivity) {
        this.numberOfNodes = numberOfNodes;
        this.minNodeWeight = minNodeWeight;
        this.maxNodeWeight = maxNodeWeight;
        this.connectivity = connectivity;
    }

    @Parameters
    public static Collection getParams() {
        Object[][] params = new Object[][]{{5, 1, 5, 0.5},
                                           {10, 5, 10, 0.9}};
        return Arrays.asList(params);
    }

    @Test
    public void testFactory() {
        Graph graph = GraphFactory.newGraph(numberOfNodes, minNodeWeight, maxNodeWeight, connectivity);
        Validator validator = new CyclingValidator();
        assertTrue(graph.getNodes().size() == numberOfNodes);
        for (Node node : graph.getNodes()) {
            final int weight = node.getWeight();
            assertTrue(weight >= minNodeWeight);
            assertTrue(weight <= maxNodeWeight);
        }
        assertTrue(validator.isValid(graph.getNodes(), graph.getLinks()));
    }
}
