package test.edu.kpi.pzks.core.validator;

import edu.kpi.pzks.core.exceptions.ValidationException;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.validator.ConsistencyValidator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @author smarx
 */

public class ConsistencyValidatorTest {

    @Test
    public void testValidatesSingleNodeGraph() throws Exception {
        ConsistencyValidator validator = new ConsistencyValidator();

        Collection<Node> nodes = Arrays.asList(new Node[]{new Node()});
        Collection<Link> links = new ArrayList<>();

        validator.validateConsistency(nodes, links);
    }

    @Test(expected = ValidationException.class)
    public void testInvalidatesDualNodeUnlinkedGraph() {
        ConsistencyValidator validator = new ConsistencyValidator();

        Node nodeA = new Node();
        Node nodeB = new Node();
        Collection<Node> nodes = Arrays.asList(new Node[]{nodeA, nodeB});
        Collection<Link> links = Arrays.asList(new Link[]{});

        validator.validateConsistency(nodes, links);
    }

    @Test()
    public void testValidatesDualNodeGraph() {
        ConsistencyValidator validator = new ConsistencyValidator();

        Node nodeA = new Node();
        Node nodeB = new Node();
        Collection<Node> nodes = Arrays.asList(new Node[]{nodeA, nodeB});
        Collection<Link> links = Arrays.asList(new Link[]{new Link(nodeA, nodeB)});

        validator.validateConsistency(nodes, links);
    }


    @Test(expected = ValidationException.class)
    public void testInvalidatesDualComponentGraph() {
        ConsistencyValidator validator = new ConsistencyValidator();

        Node nodeA = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();
        Node nodeD = new Node();
        Collection<Node> nodes = Arrays.asList(new Node[]{nodeA, nodeB, nodeC, nodeD});
        Collection<Link> links = Arrays.asList(new Link[]{new Link(nodeA, nodeB), new Link(nodeC, nodeD)});

        validator.validateConsistency(nodes, links);
    }


    @Test()
    public void testValidatesFullyConnectedGraph() {
        ConsistencyValidator validator = new ConsistencyValidator();

        Node nodeA = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();
        Node nodeD = new Node();
        Collection<Node> nodes = Arrays.asList(new Node[]{nodeA, nodeB, nodeC, nodeD});
        Collection<Link> links = Arrays.asList(new Link[]{
                new Link(nodeA, nodeB), new Link(nodeA, nodeC), new Link(nodeA, nodeD),
                new Link(nodeB, nodeC), new Link(nodeB, nodeD), new Link(nodeC, nodeD)
        });

        validator.validateConsistency(nodes, links);
    }
}
