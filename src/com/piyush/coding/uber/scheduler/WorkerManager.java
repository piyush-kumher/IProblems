package com.piyush.coding.uber.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkerManager {

    private static final int DEFAULT_WORKER_SIZE = 1;
    private final int maxWorkerSize;
    private final Map<String, Worker> workers;
    private final AtomicInteger workerCount = new AtomicInteger();

    public WorkerManager(int workerSize) {
        this.maxWorkerSize = workerSize;
        this.workers = new HashMap<>();
    }

    public WorkerManager() {
        this(DEFAULT_WORKER_SIZE);
    }

    public void initializeAllWorkers(TaskManager taskManager) {
        for (int i = 0; i < maxWorkerSize; i++) {
            Worker w = new Worker(taskManager);
            workerCount.incrementAndGet();
            workers.put(w.getId(), w);
            w.thread.start();
        }
    }

    public boolean addWorker(Worker worker) {
        if (workerCount.incrementAndGet() >= maxWorkerSize) {
            workerCount.decrementAndGet();
            return false;
        }
        workers.put(worker.getId(), worker);
        return true;
    }

}
