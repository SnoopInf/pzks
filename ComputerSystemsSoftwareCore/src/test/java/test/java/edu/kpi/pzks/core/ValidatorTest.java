package test.java.edu.kpi.pzks.core;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Links;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.validator.ConsistencyValidator;
import edu.kpi.pzks.core.validator.CyclingValidator;
import edu.kpi.pzks.core.validator.Validator;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Aloren
 */
public class ValidatorTest {

    List<Node> nodes;
    Links links;

    @Before
    public void before() {
        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();

        Link l12 = new Link(n1, n2);
        Link l23 = new Link(n2, n3);
        Link l31 = new Link(n3, n1);

        nodes = Arrays.asList(new Node[]{n1, n2, n3});
        links = new Links(Arrays.asList(l12, l23, l31));
    }

    @Test
    public void testCyclingValidator1221() {
        Validator validator = new CyclingValidator();
        boolean valid = validator.isValid(nodes, links);
        assertFalse(valid);

        Node n1 = new Node();
        Node n2 = new Node();

        Link l12 = new Link(n1, n2);
        Link l21 = new Link(n2, n1);

        nodes = Arrays.asList(new Node[]{n1, n2});
        links = new Links(Arrays.asList(l12, l21));

        valid = validator.isValid(nodes, links);
        assertFalse(valid);
    }

    @Test
    public void testCyclingValidator122123() {
        Validator validator = new CyclingValidator();
        boolean valid = validator.isValid(nodes, links);
        assertFalse(valid);

        Node n1 = new Node();
        Node n2 = new Node();

        Link l12 = new Link(n1, n2);
        Link l21 = new Link(n2, n1);

        Node n3 = new Node();

        Link l23 = new Link(n2, n3);

        nodes = Arrays.asList(new Node[]{n1, n2, n3});
        links = new Links(Arrays.asList(l12, l21, l23));

        valid = validator.isValid(nodes, links);
        assertFalse(valid);

    }

    @Test
    public void testCyclingValidator122123AndOneNode() {
        Validator validator = new CyclingValidator();
        boolean valid = validator.isValid(nodes, links);
        assertFalse(valid);

        Node n1 = new Node();
        Node n2 = new Node();

        Link l12 = new Link(n1, n2);
        Link l21 = new Link(n2, n1);

        Node n3 = new Node();
        Node n4 = new Node();

        Link l23 = new Link(n2, n3);

        nodes = Arrays.asList(new Node[]{n1, n2, n3, n4});
        links = new Links(Arrays.asList(l12, l21, l23));

        valid = validator.isValid(nodes, links);
        assertFalse(valid);
    }

    @Test
    public void testConsistencyValidator() {
        Validator validator = new ConsistencyValidator();

        boolean noErrors = validator.isValid(nodes, links);
        assertTrue(noErrors);
    }
}
