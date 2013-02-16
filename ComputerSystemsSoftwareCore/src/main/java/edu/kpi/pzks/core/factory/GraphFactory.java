package edu.kpi.pzks.core.factory;

import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.validator.CyclingValidator;
import edu.kpi.pzks.core.validator.Validator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Aloren
 */
public class GraphFactory {

    private static int nodesWeightSum;

    public static Graph newGraph(int numberOfNodes, int minNodeWeight, int maxNodeWeight, double connectivity) {
        if (connectivity > 1 || connectivity < 0) {
            throw new IllegalArgumentException("Connectivity = " + connectivity + ". connectivity <=1 and >=0");
        }
        //TODO check other params

        Validator validator = new CyclingValidator();
        Graph graph = new Graph();
        Random generator = new Random();
        nodesWeightSum = 0;
        List<Node> nodes = generateNodes(numberOfNodes, generator, maxNodeWeight, minNodeWeight);
        
        double linksWeightSum = (double) (nodesWeightSum / connectivity) - nodesWeightSum;
        double remainder = linksWeightSum;
        
        List<Link> links = new LinkedList<>();
        
        for (int i = 0; i < nodes.size(); i++) {
            Node fromNode, toNode;
            Link link = null;
            int weight;
            //TODO check next code
            do {
                links.remove(link);
                fromNode = nodes.get(generator.nextInt(nodes.size()));
                toNode = nodes.get(generator.nextInt(nodes.size()));
                weight = generateWeight(generator, 0, (int) remainder);
                link = new Link(fromNode, toNode, weight);
                links.add(link);
            } while (!validator.isValid(nodes, links));
            remainder = linksWeightSum - weight;
        }
        graph.addNodes(nodes);
        graph.addLinks(links);
        return graph;
    }

    protected static List<Node> generateNodes(int numberOfNodes, Random generator, int maxNodeWeight, int minNodeWeight) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            int weight = generateWeight(generator, minNodeWeight, maxNodeWeight);
            nodesWeightSum += weight;
            Node node = new Node(weight);
            nodes.add(node);
        }
        return nodes;
    }

    private static int generateWeight(Random generator, int minNodeWeight, int maxNodeWeight) {
        int weight = generator.nextInt(maxNodeWeight) + minNodeWeight;
        return weight;
    }
}
