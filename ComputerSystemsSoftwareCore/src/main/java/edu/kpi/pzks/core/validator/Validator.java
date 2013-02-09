package edu.kpi.pzks.core.validator;


import edu.kpi.pzks.core.exceptions.ValidationException;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import java.util.*;

/**
 * Author: Kirill Davidenko Date: 07.02.13 Time: 21:14
 */
public class Validator {

    public void validate(List<Node> nodes, List<Link> links) {
        validateCycles(nodes, links);
        validateConsistency(nodes, links);
    }

    /*
     * Checks for cycles. I suppose that cycle is when we have path from child to one of it's parents.
     */
    public void validateCycles(List<Node> nodes, List<Link> edges) {
        if (edges.isEmpty()) {
            return;
        }

        List<Node> roots = new ArrayList<Node>(nodes);
        Set<Node> notRoots = new HashSet<Node>();
        for (Link edge : edges) {
            notRoots.add(edge.getToNode());
        }
        roots.removeAll(notRoots);
        for (Node root : roots) {
            deepSearch(root, new HashSet<Node>(Arrays.asList(root)));
        }

    }

    private void deepSearch(Node root, Set<Node> path) {
        for (Node outputNodes : root.getOutputNodes()) {
            if (path.contains(outputNodes)) {
                throw new ValidationException("Граф имеет цикл!");
            }
            path.add(root);
            deepSearch(root, new HashSet<Node>(path));
        }
    }


    /* Checks for consistency.
    *  I suppose that graph is inconsistent if there's a single Node which has no input and output.
    */
    public void validateConsistency(List<Node> nodes, List<Link> edges) {
        if (nodes.size() < 2) {
            return;
        }

        for (Node node : nodes) {
            boolean hasEdge = false;
            for (Link edge : edges) {
                if (edge.getFromNode().equals(node) || edge.getToNode().equals(node)) {
                    hasEdge = true;
                }
            }
            if (!hasEdge) {
                throw new ValidationException("Граф не связан");
            }
        }
    }
}
