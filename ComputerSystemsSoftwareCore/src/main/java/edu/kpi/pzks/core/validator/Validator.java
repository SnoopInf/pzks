package edu.kpi.pzks.core.validator;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import java.util.Collection;

/**
 * Author: Kirill Davidenko Date: 12.02.13 Time: 00:10
 */
public interface Validator {

    /**
     * Returns true if graph is validated.
     * Returns true if it consists errors.
     */
    boolean validate(Collection<Node> nodes, Collection<Link> links);
}
