package com.atos.upsvar.modules.rx.model.executor;

/**
 * Created by a605053 on 11. 1. 2018.
 */

import java.util.concurrent.Executor;

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the out of the UI thread.
 */
public interface ThreadExecutor extends Executor {}
