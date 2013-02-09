package edu.kpi.pzks.core.model;

import java.io.Serializable;

/**
 *
 * @author Aloren
 */
public abstract class GraphObject implements Serializable {

    protected int weight;

    public GraphObject(int weigth) {
        this.weight = weigth;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
