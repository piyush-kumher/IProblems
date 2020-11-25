package com.piyush.coding.uber.scheduler;

public class RejectedQueueException extends RuntimeException {

    private static final String ERROR_MESSAGE_FORMAT =
            "Queue size is %s, max size allowed is: %s, can't add any more task.. shutting down";

    public RejectedQueueException(long maxSize, long currentSize) {
        super(String.format(ERROR_MESSAGE_FORMAT, maxSize, currentSize));
    }

}
