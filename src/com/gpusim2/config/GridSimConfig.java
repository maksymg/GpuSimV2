package com.gpusim2.config;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: maksym
 * Date: 10/20/13
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class GridSimConfig implements Serializable {

    public static final int CURRENT_CONFIG_VERSION = 1;

    private int version;
    private double linkBaudRate;
    private LinkedList<GridSimResourceConfig> resources;
    private LinkedList<GridSimGridletConfig> gridlets;

    public GridSimConfig() {
        this.version = CURRENT_CONFIG_VERSION;
        this.linkBaudRate = 1.0;
        this.resources = new LinkedList<GridSimResourceConfig>();
        this.gridlets = new LinkedList<GridSimGridletConfig>();
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public double getLinkBaudRate() {
        return linkBaudRate;
    }

    public void setLinkBaudRate(double linkBaudRate) {
        this.linkBaudRate = linkBaudRate;
    }

    public LinkedList<GridSimResourceConfig> getResources() {
        return resources;
    }

    public void setResources(LinkedList<GridSimResourceConfig> resources) {
        this.resources = resources;
    }

    public LinkedList<GridSimGridletConfig> getGridlets() {
        return gridlets;
    }

    public void setGridlets(LinkedList<GridSimGridletConfig> gridlets) {
        this.gridlets = gridlets;
    }
}
