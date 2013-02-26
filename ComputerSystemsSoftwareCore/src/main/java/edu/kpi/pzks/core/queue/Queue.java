package edu.kpi.pzks.core.queue;

import java.util.Collection;

/**
 * @author smarx
 */
public interface Queue {
    Collection<QueuedNode> evaluate();
}
