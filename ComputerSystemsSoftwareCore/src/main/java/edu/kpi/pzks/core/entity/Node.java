package edu.kpi.pzks.core.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Kirill Davidenko Date: 07.02.13 Time: 21:24
 */
public class Node {
    private List<Node> children = new LinkedList<Node>();

    public void addChild(Node node) {
        children.add(node);
    }

    public void removeChild(Node node) {
        children.remove(node);
    }

    public List<Node> getChildren() {
        return new LinkedList<Node>(children);
    }
}
