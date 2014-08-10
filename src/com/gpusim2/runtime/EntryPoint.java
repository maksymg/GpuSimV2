package com.gpusim2.runtime;

import gridsim.GridSim;

/**
 * Created with IntelliJ IDEA.
 * User: maksym
 * Date: 10/20/13
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntryPoint {


    public static void main(String[] args) throws Exception {
        GridSimRuntime gridSimRuntime1 = new GridSimRuntime();
        gridSimRuntime1.loadConfig(args[0]);
        gridSimRuntime1.initGridSim();
        gridSimRuntime1.createResources();
        gridSimRuntime1.createGridletsContainer();
        gridSimRuntime1.createUser(gridSimRuntime1);
        GridSim.startGridSimulation();
        gridSimRuntime1.saveOutput(args[1]);



    }


}

class Runnable1 implements Runnable {
    public void run() {
        synchronized (GridSim.class) {
            GridSim.startGridSimulation();
        }
    }
}

class Runnable2 implements Runnable {
    public void run() {
        synchronized (GridSim.class) {
            GridSim.startGridSimulation();
        }
    }
}
