package edu.kpi.pzks.core.model;

import java.io.Serializable;

/**
 *
 * @author Aloren
 */
public abstract class GraphObject implements Serializable {

    protected int weight;

    public GraphObject() {
        this(0);
    }

    public GraphObject(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
