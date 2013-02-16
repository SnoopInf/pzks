package edu.kpi.pzks.core.validator;

import edu.kpi.pzks.core.exceptions.ValidationException;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.util.Messages;
import java.util.*;

/**
 * Author: Kirill Davidenko Date: 12.02.13 Time: 00:10
 */
public class CyclingValidator implements Validator {

    @Override
    public boolean isValid(Collection<Node> nodes, Collection<Link> links) {
        return validateCycles(nodes, links);
    }

    /*
     * Checks for cycles. I suppose that cycle is when we have path from child to one of it's parents.
     */
    protected boolean validateCycles(Collection<Node> nodes, Collection<Link> edges) {
        if (edges.isEmpty()) {
            return true;
        }

        List<Node> roots = new ArrayList<>(nodes);
        Set<Node> notRoots = new HashSet<>();

        for (Link edge : edges) {
            notRoots.add(edge.getToNode());
        }
        roots.removeAll(notRoots);

        if (roots.isEmpty()) {
            return false;
//            throw new ValidationException(Messages.getLocalizedMessage(Messages.VALIDATION_ERROR_CYCLES_PRESENT, Locale.getDefault()));
        }

        try {
            for (Node root : roots) {
                deepSearch(root, new HashSet<>(Arrays.asList(root)));
            }
        } catch (ValidationException e) {
            return false;
        }
        return true;

    }

    protected void deepSearch(Node root, Set<Node> path) throws ValidationException {
        for (Node outputNode : root.getOutputNodes()) {
            if (path.contains(outputNode)) {
                throw new ValidationException(Messages.getLocalizedMessage(Messages.VALIDATION_ERROR_CYCLES_PRESENT, Locale.getDefault()));
            }
            path.add(root);
            deepSearch(outputNode, new HashSet<>(path));
        }
    }
}
