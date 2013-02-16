package test.java.edu.kpi.pzks.core;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.validator.ConsistencyValidator;
import edu.kpi.pzks.core.validator.CyclingValidator;
import edu.kpi.pzks.core.validator.Validator;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Aloren
 */
public class ValidatorTest {

    List<Node> nodes;
    List<Link> links;

    @Before
    public void before() {
        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();

        Link l12 = new Link(n1, n2);
        Link l23 = new Link(n2, n3);
        Link l31 = new Link(n3, n1);

        nodes = Arrays.asList(new Node[]{n1, n2, n3});
        links = Arrays.asList(new Link[]{l12, l23, l31});
    }

    @Test
    public void testCyclingValidator() {
        Validator validator = new CyclingValidator();


        boolean noErrors = validator.isValid(nodes, links);
        assertFalse(noErrors);

    }

    @Test
    public void testConsistencyValidator() {
        Validator validator = new ConsistencyValidator();

        boolean noErrors = validator.isValid(nodes, links);
        assertTrue(noErrors);
    }
}
