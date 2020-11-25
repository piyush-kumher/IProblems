package com.piyush.coding.uber.scheduler;

public class Test {

    public static void main(String[] args) throws Exception {
        ScheduledTaskExecutor t = new ScheduledTaskExecutor(2);
        Test1 test1 = new Test1();
        t.scheduleAtAFixedRate(test1::printSomething, 1000, 15000);

        Thread.sleep(10 * 60 * 1000L);
    }
}
