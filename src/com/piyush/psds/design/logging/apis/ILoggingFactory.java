package com.piyush.psds.design.logging.apis;

/**
 * Will provide an instance of {@link Logger} Object.
 */
public interface ILoggingFactory {

    /**
     * Return an object of Logger which will help log messages associated with the name
     * @param name the name of the Logger
     * @return {@link Logger} object
     */
    public Logger getLogger(String name);

}
