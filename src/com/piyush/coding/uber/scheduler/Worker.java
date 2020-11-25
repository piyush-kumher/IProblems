package com.piyush.coding.uber.scheduler;

import java.util.UUID;

public class Worker implements Runnable {

    String id;
    Task task;
    Thread thread;
    TaskManager taskManager;

    public Worker(Task task, TaskManager taskManager) {
        this.id = UUID.randomUUID().toString();
        this.task = task;
        this.taskManager = taskManager;
        this.thread = new Thread(this);
    }

    public Worker(TaskManager taskManager) {
        this(null, taskManager);
    }

    @Override
    public void run() {
        runTask();
    }

    private void runTask() {
        while (task != null || (task = taskManager.getTask()) != null) {
            task.run();
            task = null;
            task.setNextStartTime();
            taskManager.addTask(task);
        }
    }

    public String getId() {
        return id;
    }
}
