package edu.kpi.pzks.core.queue;

import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author smarx
 */
public abstract class AbstractFactorEvaluator implements FactorEvaluator {

    private Collection<Node> nodes;
    private Collection<Link> links;

    public AbstractFactorEvaluator(Collection<Node> nodes, Collection<Link> links) {
        this.nodes = nodes;
        this.links = links;
    }

    //TODO do we really need this method?
/*    protected boolean isOnCriticalPath(){
        return false;
    }*/

    protected int getCriticalPathWeightForGraph() {
        List<Node> criticalPathForGraph = getCriticalPathForGraph();

        return calculateCriticalPath(criticalPathForGraph);
    }

    private List<Node> getCriticalPathForGraph() {
        int criticalWeight = 0;
        List<Node> criticalPath = null;
        for(Node n : nodes) {
            if(n.getOutputNodes().size() == 0) {
                List<Node> currentPath = findPathToRootFrom(n);
                int currentWeight = calculateCriticalPath(currentPath);
                if(currentWeight > criticalWeight) {
                    criticalWeight = currentWeight;
                    criticalPath = currentPath;
                }
            }
        }
        return criticalPath;
    }

    private int calculateCriticalPath(List<Node> path) {
        int weight = 0;
        for(Node node : path) {
            weight += node.getWeight();
        }
        return weight;
    }

    protected List<Node> findPathToRootFrom(Node n) {
        Collection<Node> inNodes = n.getInputNodes();
        int criticalWeight = 0;
        List<Node> criticalPath = new ArrayList<>();

        for(Node current : inNodes) {
            List<Node> currentPath = findPathToRootFrom(current);
            int weight = calculateCriticalPath(currentPath);
            if(weight > criticalWeight) {
                criticalPath = currentPath;
                criticalWeight = weight;
            }
        }

        criticalPath.add(0, n);
        return criticalPath;
    }

    /*protected void printPath(List<Node> path, int criticalWeight) {
        for (Node n: path) {
            System.out.print(n.getId()+"@"+n.getWeight()+"  ");
        }
        System.out.println("/ "+criticalWeight);
    }*/

    protected int getCriticalPathFromTopTo(Node n) {
        List<Node> criticalPath = findPathToRootFrom(n);
        int critical = calculateCriticalPath(criticalPath);
        return critical;
    }

    /*protected int getCriticalPathFromBottom(Node n) {
        return 0;
    }*/

    protected double getWeightedCriticalPathFromTopTo(Node n) {
        return (getCriticalPathFromTopTo(n) + .0d)/ getCriticalPathWeightForGraph();
    }

    /*protected double getWeightedCriticalPathFromBottom(Node n) {
        return 0;
    }*/
    
    protected int getCriticalNumberForGraph() {
        return getCriticalPathForGraph().size();
    }

    protected int getCriticalNumberFromTopTo(Node n) {
        return findPathToRootFrom(n).size();
    }

    /*protected int getCriticalNumberFromBottom(Node n) {
        return 0;
    }*/

    protected double getWeightedCriticalNumberFromTopTo(Node n) {
        return ((double)getCriticalNumberFromTopTo(n))/getCriticalNumberForGraph();
    }

    /*protected double getWeightedCriticalNumberFromBottom(Node n) {
        return 0;
    }*/

    /**
     * @param n Node which needs to have it's root node retrieved
     * @return Node which has a simple path to a given node and has no inbound links
     */
    //TODO do we really need this method?
    /*protected Node getRootNodeFor(Node n) {
        return n;
    }*/

}
