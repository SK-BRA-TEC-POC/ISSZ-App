package com.atos.issr.modules.rx.model.interactor.useCase;

import com.atos.issr.modules.rx.model.executor.PostExecutionThread;
import com.atos.issr.modules.rx.model.executor.ThreadExecutor;
import com.atos.issr.modules.rx.model.interactor.UseCase;
import com.atos.issr.modules.rx.model.ws.dtos.request.LegalPersonRequest;

import rx.Observable;

/**
 * Created by a605053 on 12. 1. 2018.
 */

public class LegalPersonService extends UseCase<LegalPersonRequest> {
    private static final String TAG = LegalPersonService.class.getName();

    public LegalPersonService() {
        super();
    }

    public LegalPersonService(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(LegalPersonRequest request) {
        return issrAPI.legalPersonRequestCall(request);
    }

}
