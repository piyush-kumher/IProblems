package com.piyush.coding.uber.scheduler;

public class ScheduledTaskExecutor {

    private static final Integer DEFAULT_THREAD_POOL_SIZE = 1;
    private WorkerManager workerManager;
    private TaskManager taskManager;

    public ScheduledTaskExecutor() {
        this(DEFAULT_THREAD_POOL_SIZE);
    }

    public ScheduledTaskExecutor(int coreThreadPoolSize) {
        this.taskManager = new TaskManager();
        this.workerManager = new WorkerManager(coreThreadPoolSize);
        workerManager.initializeAllWorkers(taskManager);
    }

    public void scheduleAtAFixedRate(Runnable command, long t, long period) {
        Task task = new Task(command, (System.currentTimeMillis() + t), period, true);
        taskManager.addTask(task);
    }

}
