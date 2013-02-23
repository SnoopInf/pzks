package edu.kpi.pzks.core.model;

import java.io.Serializable;

/**
 *
 * @author Aloren
 */
public abstract class GraphObject implements Serializable {

    protected int weight;
    protected String name = "";

    public GraphObject() {
        this(1);
    }

    public GraphObject(int weight) {
        checkWeight(weight);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        checkWeight(weight);
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public abstract String toString();

    private void checkWeight(int weight) {
        if (weight < 1) {
            throw new IllegalArgumentException("Weight must be >=1, but was " + weight);
        }
    }
}
