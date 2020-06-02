package com.atos.issr.modules.rx.model.interactor;

/**
 * Created by a605053 on 11. 1. 2018.
 */


import com.atos.issr.modules.rx.model.executor.PostExecutionThread;
import com.atos.issr.modules.rx.model.executor.ThreadExecutor;
import com.atos.issr.modules.rx.model.executor.ThreadManager;
import com.atos.issr.modules.rx.model.ws.UpsvarAPI;
import com.atos.issr.modules.rx.model.ws.UpsvarApiImpl;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link rx.Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class UseCase<T> {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    protected final UpsvarAPI upsvarAPI = new UpsvarApiImpl();

    private Subscription subscription = Subscriptions.empty();

    protected UseCase(){
        this(ThreadManager.provideThreadExecutor(), ThreadManager.providePostExecutionThread());
    }

    protected UseCase(ThreadExecutor threadExecutor,
                      PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }


    /**
     * Builds an {@link rx.Observable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Observable buildUseCaseObservable(T params);

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build
     * with {@link #buildUseCaseObservable()}.
     */
    @SuppressWarnings("unchecked")
    public void execute(Subscriber useCaseSubscriber, T params) {
        this.subscription = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(useCaseSubscriber);
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
