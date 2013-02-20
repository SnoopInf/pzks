package edu.kpi.pzks.core.validator;

import edu.kpi.pzks.core.exceptions.ValidationException;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.util.Messages;

import java.util.*;

/**
 * Author: Kirill Davidenko Date: 07.02.13 Time: 21:14
 */
public class SubGraphValidator implements Validator {

    @Override
    public boolean isValid(Collection<Node> nodes, Collection<Link> links) {
        try {
            validateConsistency(nodes, links);
            return true;
        } catch (ValidationException e) {
            return false;
        }
    }

    @Override
    public void validate(Collection<Node> nodes, Collection<Link> links) {
        validateConsistency(nodes, links);
    }

    /* Checks for consistency.
     *  I suppose that graph is inconsistent if there's a single Node which has no input and output.
     */
    public void validateConsistency(Collection<Node> nodes, Collection<Link> edges) {
        Set<Node> firstGraph = new HashSet<Node>();

        if (nodes.isEmpty()) {
            return;
        }

        List<Node> allNodes = new ArrayList<>(nodes);

        Node firstNode = allNodes.get(0);
        firstGraph.add(firstNode);

        addAllChildren(firstNode, firstGraph);
        if (firstGraph.size() < nodes.size()) {
            throw new ValidationException(Messages.getLocalizedMessage(Messages.VALIDATION_ERROR_SUBGRAPHS_PRESENT, Locale.getDefault()));
        }

    }

    private void addAllChildren(Node firstNode, Set<Node> firstGraph) {
        for (Node child : firstNode.getOutputNodes()) {
            if (!firstGraph.contains(child)) {
                firstGraph.add(child);
                addAllChildren(child, firstGraph);
            }
        }
        for (Node child : firstNode.getInputNodes()) {
            if (!firstGraph.contains(child)) {
                firstGraph.add(child);
                addAllChildren(child, firstGraph);
            }
        }
    }


}
