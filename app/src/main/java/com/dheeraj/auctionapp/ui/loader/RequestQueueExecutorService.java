package com.dheeraj.auctionapp.ui.loader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DBhati on 11-Jun-15.
 */
public class RequestQueueExecutorService {

    private int THREAD_POOL_SIZE = 10; // will later improve this logic based on CPU nos.
    ExecutorService mExecutorService =  Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    void addToQueue(Runnable request) {
        mExecutorService.submit(request);
    }
}
