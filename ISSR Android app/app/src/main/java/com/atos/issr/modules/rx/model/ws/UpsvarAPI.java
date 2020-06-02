package com.atos.issr.modules.rx.model.ws;


import com.atos.issr.modules.rx.model.ws.dtos.request.UpsvarRequest;
import com.atos.issr.modules.rx.model.ws.dtos.response.UpsvarResponse;

import rx.Observable;

/**
 * Created by a605053 on 11. 1. 2018.
 */

public interface UpsvarAPI {
    Observable<UpsvarResponse> call(UpsvarRequest request);
}
