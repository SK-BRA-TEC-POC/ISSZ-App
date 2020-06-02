package com.atos.issr.modules.rx.model.ws;

import android.util.Log;

import com.atos.issr.BuildConfig;
import com.atos.issr.modules.rx.model.ws.dtos.request.UpsvarRequest;
import com.atos.issr.modules.rx.model.ws.dtos.response.UpsvarResponse;
import com.atos.issr.utils.StringUtils;

import java.net.MalformedURLException;

import rx.Observable;


/**
 * Created by a605053 on 11. 1. 2018.
 */

public class UpsvarApiImpl implements UpsvarAPI {
    public static final String TAG = "UpsvarApiImpl";

    @Override
    public Observable<UpsvarResponse> call(UpsvarRequest request) {
        return rx.Observable.create(subscriber -> {
            try {
                UpsvarResponse response = callWS(request, UpsvarResponse.class, "/url suffix");
                if (response == null) {
                    throw new NullPointerException();
                }
                subscriber.onNext(response);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    private <T> T callWS(UpsvarRequest request, Class<T> clazz, String urlSuffix) {
        try {
            ApiConnection apiConnection = ApiConnection.createGET(BuildConfig.SERVER_BASE_URL + urlSuffix);
            String sRequest = StringUtils.requestToJson(request);
            Log.i(TAG, "Sending request: " + sRequest);
            String result = apiConnection.connectToApi(sRequest);
            Log.i(TAG, "Received response: " + result);
            if (result == null) {
                throw new NullPointerException("WS call result is null. Check connection");
            }
            return StringUtils.responseFromJson(clazz, result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
