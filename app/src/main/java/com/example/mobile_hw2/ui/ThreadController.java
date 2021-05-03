package com.example.mobile_hw2.ui;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadController {

    private static final ThreadController INSTANCE = new ThreadController();
    private final ThreadPoolExecutor poolExecutor;

    private ThreadController() {
        BlockingDeque<Runnable> runnables = new LinkedBlockingDeque<>();
        poolExecutor = new ThreadPoolExecutor(5,10, 1000, TimeUnit.MILLISECONDS, runnables);
    }

    public void submitTask(Runnable task) {
        poolExecutor.submit(task);
    }

    public boolean isPoolFull() {
        return poolExecutor.getPoolSize() == poolExecutor.getMaximumPoolSize();
    }

    public static ThreadController getInstance() {
        return INSTANCE;
    }
}