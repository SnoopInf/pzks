package edu.kpi.pzks.core.validator;

import edu.kpi.pzks.core.entity.Edge;
import edu.kpi.pzks.core.entity.Node;

import java.util.*;

/**
 * Author: Kirill Davidenko Date: 07.02.13 Time: 21:14
 */
public class Validator {
    public void validate(int[][] incidenceMatrix) {
        validateCycles(incidenceMatrix);
        validateConsistency(incidenceMatrix);
    }

    public void validate(List<Node> nodes, List<Edge> edges) {
        validateCycles(nodes, edges);
        validateConsistency(nodes, edges);
    }

    /*
     * Checks for cycles. I suppose that cycle is when we have path from child to one of it's parents.
     */
    public void validateCycles(int[][] incidenceMatrix) {
        if (incidenceMatrix.length < 2) {
            return;
        }
        List<Node> nodes = new ArrayList<Node>(incidenceMatrix.length);
        List<Edge> edges = new ArrayList<Edge>();
        //init Nodes
        for (int[] row : incidenceMatrix) {
            nodes.add(new Node());
        }
        //init edges
        for (int i = 0; i < incidenceMatrix.length; i++) {
            for (int j = 0; j < incidenceMatrix.length; j++) {
                if (incidenceMatrix[i][j] == 1) {
                    edges.add(new Edge(nodes.get(i), nodes.get(j)));
                }
            }
        }
        validateCycles(nodes, edges);
    }

    /*
     * Checks for cycles. I suppose that cycle is when we have path from child to one of it's parents.
     */
    public void validateCycles(List<Node> nodes, List<Edge> edges) {
        if (edges.isEmpty()) {
            return;
        }

        List<Node> roots = new ArrayList<Node>(nodes);
        Set<Node> notRoots = new HashSet<Node>();
        for (Edge edge : edges) {
            notRoots.add(edge.getTo());
        }
        roots.removeAll(notRoots);
        for (Node root : roots) {
            deepSearch(root, new HashSet<Node>(Arrays.asList(root)));
        }

    }

    private void deepSearch(Node root, Set<Node> path) {
        for (Node child : root.getChildren()) {
            if (path.contains(child)) {
                throw new ValidationException("Граф имеет цикл!");
            }
            path.add(root);
            deepSearch(root, new HashSet<Node>(path));
        }
    }


    /* Checks for consistency.
    *  I suppose that graph is inconsistent if there's a single Node which has no input and output.
    */
    public void validateConsistency(int[][] incidenceMatrix) {
        if (incidenceMatrix.length < 2) {
            return;
        }

        int rowIndex = 0;
        for (int[] row : incidenceMatrix) {
            int rowSum = 0;
            for (int value : row) {
                rowSum += Math.abs(value);
            }
            if (rowSum == 0) {
                int columnSum = 0;
                for (int i = 0; i < row.length; i++) {
                    columnSum += Math.abs(incidenceMatrix[rowIndex][i]);
                }
                if (columnSum == 0) {
                    throw new ValidationException("Граф не связан");
                }
            }
            rowIndex++;
        }
    }

    /* Checks for consistency.
    *  I suppose that graph is inconsistent if there's a single Node which has no input and output.
    */
    public void validateConsistency(List<Node> nodes, List<Edge> edges) {
        if (nodes.size() < 2) {
            return;
        }

        for (Node node : nodes) {
            boolean hasEdge = false;
            for (Edge edge : edges) {
                if (edge.getFrom().equals(node) || edge.getTo().equals(node)) {
                    hasEdge = true;
                }
            }
            if (!hasEdge) {
                throw new ValidationException("Граф не связан");
            }
        }
    }
}
