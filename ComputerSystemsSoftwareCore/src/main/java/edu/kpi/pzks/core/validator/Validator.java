package edu.kpi.pzks.core.validator;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;

import java.util.Collection;

/**
 * Author: snoop Date: 12.02.13 Time: 00:10
 */
public interface Validator {

    /**
     * Returns true if graph doesn't have validation errors.
     */
    boolean isValid(Collection<Node> nodes, Collection<Link> links);

    /**
     * Throws ValidationException if graph has validation errors.
     */
    void validate(Collection<Node> nodes, Collection<Link> links);
}
