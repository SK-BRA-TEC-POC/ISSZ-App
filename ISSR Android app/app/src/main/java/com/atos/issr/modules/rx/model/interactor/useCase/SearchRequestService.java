package com.atos.issr.modules.rx.model.interactor.useCase;

import com.atos.issr.modules.rx.model.executor.PostExecutionThread;
import com.atos.issr.modules.rx.model.executor.ThreadExecutor;
import com.atos.issr.modules.rx.model.interactor.UseCase;
import com.atos.issr.modules.rx.model.ws.dtos.request.SearchRequestRequest;

import rx.Observable;

/**
 * Created by a605053 on 12. 1. 2018.
 */

public class SearchRequestService extends UseCase<SearchRequestRequest> {
    private static final String TAG = SearchRequestService.class.getName();

    public SearchRequestService() {
        super();
    }

    public SearchRequestService(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(SearchRequestRequest request) {
        return issrAPI.searchRequestCall(request);
    }

}
