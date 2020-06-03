package com.atos.issr.modules.rx.model.interactor.useCase;

import com.atos.issr.modules.rx.model.executor.PostExecutionThread;
import com.atos.issr.modules.rx.model.executor.ThreadExecutor;
import com.atos.issr.modules.rx.model.interactor.UseCase;
import com.atos.issr.modules.rx.model.ws.dtos.request.ISSRRequest;

import rx.Observable;

/**
 * Created by a605053 on 12. 1. 2018.
 */

public class ExampleService extends UseCase<ISSRRequest> {
    private static final String TAG = ExampleService.class.getName();

    public ExampleService() {
        super();
    }

    public ExampleService(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(ISSRRequest request) {
        return issrAPI.call(request);
    }

}
