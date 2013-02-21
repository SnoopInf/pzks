package edu.kpi.pzks.core.validator;

import edu.kpi.pzks.core.exceptions.ValidationException;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.util.Messages;

import java.util.*;

/**
 * Author: snoop Date: 12.02.13 Time: 00:10
 */
public class CyclingValidator implements Validator {

    @Override
    public boolean isValid(Collection<Node> nodes, Collection<Link> links) {
        try {
            validateCycles(nodes, links);
            return true;
        } catch (ValidationException e) {
            return false;
        }
    }

    @Override
    public void validate(Collection<Node> nodes, Collection<Link> links) {
        validateCycles(nodes, links);
    }

    /*
    * Checks for cycles. I suppose that cycle is when we have path from child to one of it's parents.
    */
    protected void validateCycles(Collection<Node> nodes, Collection<Link> links) {

        if (links.isEmpty()) {
            return;
        }

        for (Node root : nodes) {
            deepSearch(root, new HashSet<>(Arrays.asList(root)));
        }

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
