package io.remme.java.utils;

import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executor service to run tasks in parallel
 */
@Getter
public class RemmeExecutorService {
    private static ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static ExecutorService getInstance() {
        return es;
    }
}
