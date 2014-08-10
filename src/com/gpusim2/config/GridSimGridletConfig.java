package com.gpusim2.config;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: maksym
 * Date: 10/20/13
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class GridSimGridletConfig implements Serializable {

    private double length;
    private long inputSize;
    private long outputSize;
    private int count;

    public GridSimGridletConfig() {
        this.length = 1;
        this.inputSize = 1;
        this.outputSize = 1;
        this.count = 1;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public long getInputSize() {
        return inputSize;
    }

    public void setInputSize(long inputSize) {
        this.inputSize = inputSize;
    }

    public long getOutputSize() {
        return outputSize;
    }

    public void setOutputSize(long outputSize) {
        this.outputSize = outputSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

