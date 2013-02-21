package edu.kpi.pzks.core.factory;

import edu.kpi.pzks.core.model.Graph;
import edu.kpi.pzks.core.model.Link;
import edu.kpi.pzks.core.model.Links;
import edu.kpi.pzks.core.model.Node;
import edu.kpi.pzks.core.validator.CyclingValidator;
import edu.kpi.pzks.core.validator.Validator;
import java.util.ArrayList;
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
        Graph graph = new Graph(true);
        Random generator = new Random();
        nodesWeightSum = 0;
        List<Node> nodes = generateNodes(numberOfNodes, maxNodeWeight, minNodeWeight);

        double linksWeightSum = (double) (nodesWeightSum / connectivity) - nodesWeightSum;
        double remainder = linksWeightSum;

        Links links = new Links();

        for (int i = 0; i < nodes.size(); i++) {
            Node fromNode, toNode;
            Link link = null;
            int weight;
            //TODO check next code
            do {
                if (link != null) {
                    System.out.println("Removing link: " + link);
                    links.remove(link);
                }
                fromNode = nodes.get(generator.nextInt(nodes.size()));
                toNode = nodes.get(generator.nextInt(nodes.size()));
                weight = generateWeight(0, (int) remainder);
                link = new Link(fromNode, toNode, weight);
                links.add(link);
                System.out.println("Generated link: " + link);
                if(fromNode.equals(toNode)) {
                    System.out.println("!!!!PANIC");
                    outputAllNodeData(fromNode);
//                    outputAllNodeData(toNode);
                }
                outputAllLinks(links);
            } while (!validator.isValid(nodes, links));
            remainder = linksWeightSum - weight;
        }
        graph.addNodes(nodes);
        graph.addLinks(links);
        return graph;
    }

    protected static List<Node> generateNodes(int numberOfNodes, int maxNodeWeight, int minNodeWeight) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            int weight = generateWeight(minNodeWeight, maxNodeWeight);
            nodesWeightSum += weight;
            Node node = new Node(weight);
            node.setName("N" + i);
            nodes.add(node);
            System.out.println("Generated node: " + node);
        }
        return nodes;
    }

    private static int generateWeight(int minNodeWeight, int maxNodeWeight) {
        int weight = minNodeWeight + (int)(Math.random() * ((maxNodeWeight - minNodeWeight) + 1));
        return weight;
    }

    private static void outputAllLinks(Links links) {
        System.out.println("    --All links--");
        for(Link link : links) {
            System.out.println("    "+link);
        }
        System.out.println("    -------");
    }

    private static void outputAllNodeData(Node node) {
        System.out.println(node);
        System.out.println("    Output nodes");
        for(Node nodeO : node.getOutputNodes()) {
            System.out.println("    "+nodeO);
        }
        System.out.println("    Input nodes");
        for(Node nodeI : node.getInputNodes()) {
            System.out.println("    "+nodeI);
        }
    }
}
