package com.gpusim2.runtime;

import com.gpusim2.config.GridSimGridletConfig;
import gridsim.Gridlet;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created with IntelliJ IDEA.
 * User: maksym
 * Date: 10/20/13
 * Time: 4:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class GridletsContainer {

    private LinkedList<GridSimGridletConfig> configs;
    private ListIterator<GridSimGridletConfig> itCurrengGridletConfig;
    private GridSimGridletConfig currentConfig;
    private int currentConfigGridletsCount;
    private int currentGridletID;

    public GridletsContainer() {
        reset();
    }

    public GridletsContainer(LinkedList<GridSimGridletConfig> configs) {
        setConfigs(configs);
    }

    public void setConfigs(LinkedList<GridSimGridletConfig> configs) {
        reset();

        this.configs = configs;
        if (this.configs == null) {
            return;
        }

        this.itCurrengGridletConfig = this.configs.listIterator();
        this.currentConfig = this.itCurrengGridletConfig.next();
    }

    public Gridlet getNextGridlet(int userID) throws NoMoreGridletsException {
        if ((configs == null) || (currentConfig == null))
            throw new NoMoreGridletsException();


        if (currentConfigGridletsCount >= currentConfig.getCount())
        {
            if (!itCurrengGridletConfig.hasNext())
                throw new NoMoreGridletsException();

            currentConfig = itCurrengGridletConfig.next();
            currentConfigGridletsCount = 0;
        }

        Gridlet g = new Gridlet(currentGridletID, currentConfig.getLength(),
                currentConfig.getInputSize(), currentConfig.getOutputSize());
        g.setUserID(userID);

        ++currentGridletID;
        ++currentConfigGridletsCount;
        return g;
    }

    private void reset() {
        itCurrengGridletConfig = null;
        currentConfigGridletsCount = 0;
        currentGridletID = 0;
        currentConfig = null;
    }
}
