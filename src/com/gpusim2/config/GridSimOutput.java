package com.gpusim2.config;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: maksym
 * Date: 10/20/13
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class GridSimOutput implements Serializable {

    public static final int CURRENT_OUTPUT_VERSION = 1;

    private int version;
    private double totalSimulationTime;

    public GridSimOutput() {
        this.version = CURRENT_OUTPUT_VERSION;
        this.totalSimulationTime = 0.0;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public double getTotalSimulationTime() {
        return totalSimulationTime;
    }

    public void setTotalSimulationTime(double totalSimulationTime) {
        this.totalSimulationTime = totalSimulationTime;
    }
}
