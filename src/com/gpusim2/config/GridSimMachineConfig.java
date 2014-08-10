package com.gpusim2.config;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: maksym
 * Date: 10/20/13
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class GridSimMachineConfig implements Serializable {

    private int peCount;
    private int peRating;
    private int count;

    public GridSimMachineConfig() {
        this.peCount = 1;
        this.peRating = 1;
    }

    public int getPeCount() {
        return peCount;
    }

    public void setPeCount(int peCount) {
        this.peCount = peCount;
    }

    public int getPeRating() {
        return peRating;
    }

    public void setPeRating(int peRating) {
        this.peRating = peRating;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
