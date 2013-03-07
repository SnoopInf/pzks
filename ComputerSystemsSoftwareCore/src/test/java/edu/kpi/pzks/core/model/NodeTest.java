package edu.kpi.pzks.core.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Aloren
 */
public class NodeTest {

    int nodes = 10;
    Node testNode = new Node();

    @Test
    public void testNode() {

        testNode.addInputNode(null);
        assertTrue("No null permitted", testNode.getInputNodes().isEmpty());

        testNode.addOutputNode(null);
        assertTrue("No null permitted", testNode.getOutputNodes().isEmpty());

        Node inputNode = new Node();
        for (int i = 0; i < nodes; i++) {
            testNode.addInputNode(inputNode);
        }
        assertTrue("No duplicates permitted", testNode.getInputNodes().size() == 1);

        for (int i = 0; i < nodes; i++) {
            testNode.addOutputNode(inputNode);
        }
        assertTrue("No duplicates permitted", testNode.getOutputNodes().size() == 1);


        testNode.getInputNodes().clear();
        for (int i = 0; i < nodes; i++) {
            Node inputNode2 = new Node();
            testNode.addInputNode(inputNode2);
        }
        assertTrue("Input nodes must contain all added different nodes", testNode.getInputNodes().size() == nodes);

        testNode.getOutputNodes().clear();
        for (int i = 0; i < nodes; i++) {
            Node inputNode2 = new Node();
            testNode.addOutputNode(inputNode2);
        }
        assertTrue("Output nodes must contain all added different nodes", testNode.getOutputNodes().size() == nodes);

    }
}
