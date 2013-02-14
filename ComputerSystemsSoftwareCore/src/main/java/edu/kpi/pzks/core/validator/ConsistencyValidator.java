package edu.kpi.pzks.core.validator;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import java.util.Collection;

/**
 * Author: Kirill Davidenko Date: 07.02.13 Time: 21:14
 */
public class ConsistencyValidator implements Validator {

    @Override
    public boolean validate(Collection<Node> nodes, Collection<Link> links) {
        return validateConsistency(nodes, links);
    }

    /* Checks for consistency.
     *  I suppose that graph is inconsistent if there's a single Node which has no input and output.
     */
    public boolean validateConsistency(Collection<Node> nodes, Collection<Link> edges) {
        if (nodes.size() < 2) {
            return true;
        }

        for (Node node : nodes) {
            boolean hasEdge = false;
            for (Link edge : edges) {
                if (edge.getFromNode().equals(node) || edge.getToNode().equals(node)) {
                    hasEdge = true;
                }
            }
            if (!hasEdge) {
                return false;
//                throw new ValidationException(Messages.getLocalizedMessage(Messages.VALIDATION_ERROR_GRAPH_INCONSISTENT, Locale.getDefault()));
            }
        }
        return true;
    }
}
