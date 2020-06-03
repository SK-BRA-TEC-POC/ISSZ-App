package com.atos.issr.modules.rx.model.interactor.useCase;

import com.atos.issr.modules.rx.model.executor.PostExecutionThread;
import com.atos.issr.modules.rx.model.executor.ThreadExecutor;
import com.atos.issr.modules.rx.model.interactor.UseCase;
import com.atos.issr.modules.rx.model.ws.dtos.request.CitizenRequest;

import rx.Observable;

/**
 * Created by a605053 on 12. 1. 2018.
 */

public class CitizenService extends UseCase<CitizenRequest> {
    private static final String TAG = CitizenService.class.getName();

    public CitizenService() {
        super();
    }

    public CitizenService(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(CitizenRequest request) {
        return issrAPI.citizenRequestCall(request);
    }

}
