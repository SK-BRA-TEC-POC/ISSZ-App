package com.atos.upsvar.modules.rx.model.executor;

/**
 * Created by a605053 on 11. 1. 2018.
 */
import rx.Scheduler;

/**
 * Thread abstraction created to change the execution context from any thread to any other thread.
 * Useful to encapsulate a UI Thread for example, since some job will be done in background, an
 * implementation of this interface will change context and update the UI.
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
