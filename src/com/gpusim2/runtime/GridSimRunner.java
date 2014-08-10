package com.gpusim2.runtime;

import gridsim.GridSim;

/**
 * Created by maksym on 5/10/14.
 */
public class GridSimRunner implements Runnable {

    @Override
    public void run() {
       GridSim.startGridSimulation();
    }
}
