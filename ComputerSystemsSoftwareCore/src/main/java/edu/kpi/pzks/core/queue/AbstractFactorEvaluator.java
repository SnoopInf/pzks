package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Node;

import java.util.Collection;
import java.util.Set;

/**
 * @author smarx
 */
public abstract class AbstractFactorEvaluator implements FactorEvaluator {

    private Collection<Node> nodes;

    public AbstractFactorEvaluator(Collection<Node> nodes) {
        this.nodes = nodes;
    }

    protected boolean isOnCriticalPath(){
        return false;
    }

    protected int getCriticalPathForGraph() {
        return 0;
    }

    protected int getCriticalPathFromTop(Node n) {
        return 0;
    }

    protected int getCriticalPathFromBottom(Node n) {
        return 0;
    }

    protected double getWeightedCriticalPathFromTop(Node n) {
        return (getCriticalPathFromTop(n) + .0d)/getCriticalPathForGraph();
    }

    protected double getWeightedCriticalPathFromBottom(Node n) {
        return 0;
    }
    
    protected int getCriticalNumberForGraph() {
        return 0;
    }

    protected int getCriticalNumberFromTop(Node n) {
        return 0;
    }

    protected int getCriticalNumberFromBottom(Node n) {
        return 0;
    }

    protected double getWeightedCriticalNumberFromTop(Node n) {
        return (getCriticalNumberFromTop(n) + .0d)/getCriticalNumberForGraph();
    }

    protected double getWeightedCriticalNumberFromBottom(Node n) {
        return 0;
    }

}
