package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.queue.utils.QueuedNode;

import java.util.Collection;

/**
 * @author smarx
 */
public interface Queue {
    Collection<QueuedNode> evaluate();
}
