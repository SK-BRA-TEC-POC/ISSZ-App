package com.atos.issr.modules.rx.model.ws;


import com.atos.issr.modules.rx.model.ws.dtos.request.CitizenRequest;
import com.atos.issr.modules.rx.model.ws.dtos.request.ISSRRequest;
import com.atos.issr.modules.rx.model.ws.dtos.request.LegalPersonRequest;
import com.atos.issr.modules.rx.model.ws.dtos.response.ISSRResponse;

import rx.Observable;

/**
 * Created by a605053 on 11. 1. 2018.
 */

public interface IssrAPI {
    Observable<ISSRResponse> call(ISSRRequest request);
    Observable<ISSRResponse> citizenRequestCall(CitizenRequest request);
    Observable<ISSRResponse> legalPersonRequestCall(LegalPersonRequest request);
}
