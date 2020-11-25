package com.piyush.coding.uber.scheduler;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TaskManager {

    private static final int DEFAULT_QUEUE_SIZE = (int) Math.pow(2, 5);
    private final ReentrantLock lock = new ReentrantLock();

    int queueSize;
    AtomicInteger count = new AtomicInteger();
    PriorityQueue<Task> queue;

    public TaskManager() {
        this(DEFAULT_QUEUE_SIZE);
    }

    public TaskManager(int queueSize) {
        this.queueSize = queueSize;
        queue = new PriorityQueue<>(Comparator.comparingLong(Task::getTriggerTime));
    }

    public void addTask(Task t) throws RejectedQueueException {
        synchronized ("addTask") {
            if (count.get() < queueSize - 1) {
                queue.add(t);
            } else {
                throw new RejectedQueueException(queueSize, count.get());
            }
        }
    }

    public Task getTask() {
        lock.lock();
        try {
            while (true) {
                if (!queue.isEmpty()) {
                    if (queue.peek().getTriggerTime() <= System.currentTimeMillis()) {
                        count.decrementAndGet();
                        return queue.poll();
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

}
