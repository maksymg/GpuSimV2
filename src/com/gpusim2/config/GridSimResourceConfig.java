package com.gpusim2.config;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: maksym
 * Date: 10/20/13
 * Time: 2:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class GridSimResourceConfig implements Serializable {

    private String arch;
    private String os;
    private double costPerSec;
    private double timeZone;
    private int allocPolicy;
    private double baudRate;
    private LinkedList<GridSimMachineConfig> machines;
    private int count;

    public GridSimResourceConfig() {
        this.arch = "Unnamed Architecture";
        this.os = "Unnamed OS";
        this.costPerSec = 0;
        this.timeZone = 0;
        this.allocPolicy = 0;
        this.baudRate = 1;
        this.machines = new LinkedList<GridSimMachineConfig>();
        this.count = 1;
    }

    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public double getCostPerSec() {
        return costPerSec;
    }

    public void setCostPerSec(double costPerSec) {
        this.costPerSec = costPerSec;
    }

    public double getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(double timeZone) {
        this.timeZone = timeZone;
    }

    public int getAllocPolicy() {
        return allocPolicy;
    }

    public void setAllocPolicy(int allocPolicy) {
        this.allocPolicy = allocPolicy;
    }

    public double getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(double baudRate) {
        this.baudRate = baudRate;
    }

    public LinkedList<GridSimMachineConfig> getMachines() {
        return machines;
    }

    public void setMachines(LinkedList<GridSimMachineConfig> machines) {
        this.machines = machines;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
