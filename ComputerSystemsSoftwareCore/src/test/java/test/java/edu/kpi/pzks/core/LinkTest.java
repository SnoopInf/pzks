package test.java.edu.kpi.pzks.core;

import edu.kpi.pzks.core.exceptions.ValidationException;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aloren
 */
public class LinkTest {

    Link testLinkWithoutFromToNodes = new Link();
    Node fromNode = new Node();
    Node toNode = new Node();
    Link testLink = new Link(fromNode, toNode);
    Node toNode2 = new Node();
    Link testLink2 = new Link(fromNode, toNode2);
    Link testLink3 = new Link(fromNode, toNode);

    @Test
    public void testLink() {
        testFromToNodesAreNull(testLinkWithoutFromToNodes);
        testFromToNodesGetters();
        testLinkEquals();
        testLinkHashcode();
        testFromToNodesSetters();
    }

    @Test(expected=ValidationException.class)
    public void testCyclicLinkAreProhibited() {
        new Link(fromNode, fromNode);
    }

    private void testFromToNodesAreNull(Link testLinkWithoutFromToNodes) {
        assertTrue("", testLinkWithoutFromToNodes.getFromNode() == null);
        assertTrue("", testLinkWithoutFromToNodes.getToNode() == null);
    }

    private void testFromToNodesGetters() {
        assertTrue("", testLink.getFromNode().equals(fromNode));
        assertTrue("", testLink.getToNode().equals(toNode));
    }

    private void testLinkEquals() {
        assertTrue("Non null link must not equal null", !testLink.equals(null));
        assertTrue("Different types of objects are not equal", !testLink.equals(fromNode));
        assertTrue("Different links must not be equal", !testLink.equals(testLinkWithoutFromToNodes));
        assertTrue("Object must be equal to itself", testLink.equals(testLink));
        assertTrue("Link2 has other toNode", !testLink.equals(testLink2));
        assertTrue("Links with the same contents must be equal", testLink3.equals(testLink));
    }

    private void testLinkHashcode() {
        assertTrue("Hashcode of dif objects must be diffrent", testLink.hashCode() != testLink2.hashCode());
        assertTrue("Hashcode of the links with the same contents must be equal",testLink3.hashCode() == testLink.hashCode());
    }

    private void testFromToNodesSetters() {
        testLink.setFromNode(null);
        assertTrue("", testLink.getFromNode() == null);
        testLink.setToNode(null);
        assertTrue("", testLink.getToNode() == null);

        testLink.setFromNode(fromNode);
        assertTrue("", testLink.getFromNode().equals(fromNode));
        testLink.setToNode(toNode);
        assertTrue("", testLink.getToNode().equals(toNode));
    }
}
