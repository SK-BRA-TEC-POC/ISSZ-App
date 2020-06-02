package com.atos.issr.modules.rx.model.executor;

/**
 * Created by a605053 on 11. 1. 2018.
 */

/**
 * Class that is responsible for creating the threads that are used in application.
 */
public final class ThreadManager {
    public static ThreadExecutor provideThreadExecutor() {
        return JobExecutor.getInstance();
    }

    public static PostExecutionThread providePostExecutionThread() {
        return UIThread.getInstance();
    }
}

