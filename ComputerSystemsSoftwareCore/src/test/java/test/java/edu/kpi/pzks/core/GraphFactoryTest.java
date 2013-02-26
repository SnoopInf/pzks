package test.java.edu.kpi.pzks.core;

import edu.kpi.pzks.core.factory.GraphFactory;
import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.validator.CyclingValidator;
import edu.kpi.pzks.core.validator.Validator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
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
        Random gen = new Random();
        Object[][] params = new Object[][]{
            {4, 1, 5, gen.nextDouble()},
            {10, 5, 10, gen.nextDouble()},
            {20, 1, 10, gen.nextDouble()},
            {2, 8, 13, gen.nextDouble()},
            {15, 5, 10, gen.nextDouble()},
            {40, 1, 30, gen.nextDouble()},
        };
        return Arrays.asList(params);
    }

    @Test
    public void testFactory() {
        Graph graph = GraphFactory.newGraph(numberOfNodes, minNodeWeight, maxNodeWeight, connectivity);
        Validator cyclingValidator = new CyclingValidator();
        assertTrue(graph.getNodes().size() == numberOfNodes);
        int nodeWeightSum = 0;
        for (Node node : graph.getNodes()) {
            final int weight = node.getWeight();
            nodeWeightSum += weight;
            assertTrue(weight >= minNodeWeight);
            assertTrue(weight <= maxNodeWeight);
        }
        int linkWeightSum = 0;
        for (Link link : graph.getLinks()) {
            linkWeightSum += link.getWeight();
        }
        double curConnectivity = (double) (nodeWeightSum / (double) (nodeWeightSum + linkWeightSum));
        double delta = 0.07;
        assertEquals("", connectivity, curConnectivity, delta);
        assertTrue(cyclingValidator.isValid(graph.getNodes(), graph.getLinks()));
    }
}
