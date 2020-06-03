package com.atos.issr.modules.rx.model.ws;

import android.util.Log;

import com.atos.issr.BuildConfig;
import com.atos.issr.modules.rx.model.ws.dtos.request.CitizenRequest;
import com.atos.issr.modules.rx.model.ws.dtos.request.ISSRRequest;
import com.atos.issr.modules.rx.model.ws.dtos.request.LegalPersonRequest;
import com.atos.issr.modules.rx.model.ws.dtos.response.CitizenResponse;
import com.atos.issr.modules.rx.model.ws.dtos.response.ISSRResponse;
import com.atos.issr.modules.rx.model.ws.dtos.response.LegalPersonResponse;
import com.atos.issr.utils.StringUtils;

import java.net.MalformedURLException;

import rx.Observable;


/**
 * Created by a605053 on 11. 1. 2018.
 */

public class IssrApiImpl implements IssrAPI {
    public static final String TAG = "UpsvarApiImpl";

    @Override
    public Observable<ISSRResponse> call(ISSRRequest request) {
        return rx.Observable.create(subscriber -> {
            try {
                ISSRResponse response = mockCallClientResponse(request, ISSRResponse.class);
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

    @Override
    public Observable<ISSRResponse> citizenRequestCall(CitizenRequest request) {
        return rx.Observable.create(subscriber -> {
            try {
//                ISSRResponse response = callWS(request, ISSRResponse.class, "/citizen");
                ISSRResponse response = mockCallClientResponse(request, ISSRResponse.class);
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

    @Override
    public Observable<ISSRResponse> legalPersonRequestCall(LegalPersonRequest request) {
        return rx.Observable.create(subscriber -> {
            try {
//                ISSRResponse response = callWS(request, ISSRResponse.class, "/citizen");
                ISSRResponse response = mockCallLegalPersonResponse(request, ISSRResponse.class);
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

    private <T> T mockCallClientResponse(ISSRRequest request, Class<T> clazz) {
        String sRequest = StringUtils.requestToJson(request);
        Log.i(TAG, "Sending request: " + sRequest);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CitizenResponse response = new CitizenResponse();
        return (T) response;
    }

    private <T> T mockCallLegalPersonResponse(ISSRRequest request, Class<T> clazz) {
        String sRequest = StringUtils.requestToJson(request);
        Log.i(TAG, "Sending request: " + sRequest);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LegalPersonResponse response = new LegalPersonResponse();
        return (T) response;
    }

    private <T> T callWS(ISSRRequest request, Class<T> clazz, String urlSuffix) {
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
