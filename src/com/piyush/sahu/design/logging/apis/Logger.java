package com.piyush.sahu.design.logging.apis;

/**
 * This is the main user entry point which will help in logging the messages
 */
public interface Logger {

    /**
     * Is Error log enabled for trace logging
     * @return {@link java.lang.Boolean} Object
     */
    public boolean isTraceEnabled();

    /**
     * Log messages at trace level
     * @param message, message to be logged
     * @param params, params to be replaced in the message
     */
    public void trace(String message, Object... params);


    public boolean isDebugEnabled();

    public void debug(String message, Object... params);

    public boolean isInfoEnabled();

    public void info(String message, Object... params);

    public boolean isWranEnabled();

    public void warn(String message, Object... params);

    public boolean isErrorEnabled();

    public void error(String message, Object... params);

}
