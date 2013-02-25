package edu.kpi.pzks.core.validator;

import edu.kpi.pzks.core.exceptions.ValidationException;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.util.Messages;

import java.util.ArrayList;
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

    private boolean findPath(Node fromNode, Node toNode) {
        boolean has = false;
        ArrayList<Node> checkNodes = new ArrayList<>();
        checkNodes.addAll(fromNode.getInputNodes());
        checkNodes.addAll(fromNode.getOutputNodes());
        for (Node currentNode : checkNodes) {
            if(currentNode == toNode) {
                return true;
            }
            if(currentNode != fromNode) {
                ArrayList<Node> visitedPath = new ArrayList<>();
                visitedPath.add(fromNode);
                has = has | findPathList(visitedPath, currentNode, toNode);
            }
        }
        return has;
    }

    private boolean findPathList(Collection<Node> visitedPath, Node fromNode, Node toNode) {
        boolean has = false;
        ArrayList<Node> checkNodes = new ArrayList<>();
        checkNodes.addAll(fromNode.getInputNodes());
        checkNodes.addAll(fromNode.getOutputNodes());
        for (Node currentNode : checkNodes) {
            if(currentNode == toNode) {
                return true;
            }
            if(currentNode != fromNode & !visitedPath.contains(currentNode)) {
                ArrayList<Node> fromList = new ArrayList<>(visitedPath);
                fromList.add(fromNode);
                has = has | findPathList(fromList, currentNode, toNode);
            }
        }
        return has;
    }

    // what is the purpose of edges being passed along the nodes collection?
    public void validateConsistency(Collection<Node> nodes, Collection<Link> edges) {
        if (nodes.size() == 1) {
            return;
        }

        for (Node fromNode : nodes) {
            for(Node toNode : nodes) {
                if(!findPath(fromNode, toNode)){
                    throw new ValidationException(Messages.getLocalizedMessage(Messages.VALIDATION_ERROR_GRAPH_INCONSISTENT, Locale.getDefault()));
                }
            }
        }
    }
}
