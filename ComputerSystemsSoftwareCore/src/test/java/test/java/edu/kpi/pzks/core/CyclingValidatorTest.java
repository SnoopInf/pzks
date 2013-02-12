package test.java.edu.kpi.pzks.core;

import edu.kpi.pzks.core.exceptions.ValidationException;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.validator.CyclingValidator;
import edu.kpi.pzks.core.validator.Validator;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Aloren
 */
public class CyclingValidatorTest {

    @Test(expected = ValidationException.class)
    public void testValidator() {
        Validator validator = new CyclingValidator();
        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();

        Link l12 = new Link(n1, n2);
        Link l23 = new Link(n2, n3);
        Link l31 = new Link(n3, n1);

        List<Node> nodes = Arrays.asList(new Node[]{n1, n2, n3});
        List<Link> links = Arrays.asList(new Link[]{l12, l23, l31});
        validator.validate(nodes, links);
    }
}
