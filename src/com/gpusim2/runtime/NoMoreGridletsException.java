package com.gpusim2.runtime;

/**
 * Created with IntelliJ IDEA.
 * User: maksym
 * Date: 10/20/13
 * Time: 4:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class NoMoreGridletsException extends GridSimRuntimeException {

    public NoMoreGridletsException() {
        super("No more gridlets for current configs");
    }
}
