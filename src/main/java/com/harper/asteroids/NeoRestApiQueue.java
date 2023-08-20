package com.harper.asteroids;

import com.harper.asteroids.model.NearEarthObject;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NeoRestApiQueue {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static NeoRestApiQueue INSTANCE;

    public static NeoRestApiQueue getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new NeoRestApiQueue();
        }
        return INSTANCE;
    }

    public Future<NearEarthObject> addGetNeoRequestToQueue(Callable<NearEarthObject> task) {
        return executorService.submit(task);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
