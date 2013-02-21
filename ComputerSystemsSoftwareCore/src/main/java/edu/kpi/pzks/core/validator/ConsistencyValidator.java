package edu.kpi.pzks.core.validator;

import edu.kpi.pzks.core.exceptions.ValidationException;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.util.Messages;

import java.util.Collection;
import java.util.Locale;

/**
 * @author snoop Date: 07.02.13 Time: 21:14
 */
public class ConsistencyValidator implements Validator {

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
        if (nodes.size() == 1) {
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
                throw new ValidationException(Messages.getLocalizedMessage(Messages.VALIDATION_ERROR_GRAPH_INCONSISTENT, Locale.getDefault()));
            }
        }
    }
}
