package com.atos.upsvar.modules.rx.model.executor;

/**
 * Created by a605053 on 11. 1. 2018.
 */

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * MainThread (UI Thread) implementation based on a {@link rx.Scheduler}
 * which will execute actions on the Android UI thread
 */
public class UIThread implements PostExecutionThread {
    private static UIThread instance = null;

    public static UIThread getInstance() {
        if (instance == null) {
            instance = new UIThread();
        }

        return instance;
    }

    private UIThread() {}

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
