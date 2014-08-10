package com.gpusim2.config;

/**
 * Created with IntelliJ IDEA.
 * User: maksym
 * Date: 10/20/13
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class IncompatibleVersionException extends RuntimeException {
    private int currentVersion;
    private int targetVersion;

    public IncompatibleVersionException(int currentVersion, int targetVersion) {
        super(String.format("Incopatible version (current supported version: %1$d; target version: %2$d)",
                currentVersion, targetVersion));

        this.currentVersion = currentVersion;
        this.targetVersion = targetVersion;
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(int currentVersion) {
        this.currentVersion = currentVersion;
    }

    public int getTargetVersion() {
        return targetVersion;
    }

    public void setTargetVersion(int targetVersion) {
        this.targetVersion = targetVersion;
    }
}
