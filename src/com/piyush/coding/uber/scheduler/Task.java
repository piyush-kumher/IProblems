package com.piyush.coding.uber.scheduler;

import java.util.concurrent.atomic.AtomicLong;

public class Task implements Runnable {

    private final AtomicLong sequencer = new AtomicLong();
    Runnable command;
    boolean fixedDelay;
    private long triggerTime;
    private long period;
    private int completionCount = 0;
    private boolean completed = false;

    public Task(Runnable command, long triggerTime, long period, boolean fixedDelay) {
        this.command = command;
        this.triggerTime = triggerTime;
        this.period = period;
        this.fixedDelay = fixedDelay;
    }

    public void complete() {
        setNextStartTime();
        sequencer.compareAndSet(sequencer.get(), sequencer.get() + 1);
    }

    public void setNextStartTime() {
        triggerTime = System.currentTimeMillis() + period;
    }

    public long getTriggerTime() {
        return triggerTime;
    }

    @Override
    public void run() {
        command.run();
        completionCount++;
    }

}
