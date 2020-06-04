package com.atos.issr.modules.rx.model.ws;

import android.util.Log;

import com.atos.issr.BuildConfig;
import com.atos.issr.modules.rx.model.ws.dtos.request.CitizenRequest;
import com.atos.issr.modules.rx.model.ws.dtos.request.ISSRRequest;
import com.atos.issr.modules.rx.model.ws.dtos.request.LegalPersonRequest;
import com.atos.issr.modules.rx.model.ws.dtos.request.SearchRequestRequest;
import com.atos.issr.modules.rx.model.ws.dtos.response.CitizenResponse;
import com.atos.issr.modules.rx.model.ws.dtos.response.ISSRResponse;
import com.atos.issr.modules.rx.model.ws.dtos.response.LegalPersonResponse;
import com.atos.issr.modules.rx.model.ws.dtos.response.SearchRequestResponse;
import com.atos.issr.modules.rx.model.ws.dtos.types.DetailedRequest;
import com.atos.issr.modules.rx.model.ws.dtos.types.RequestState;
import com.atos.issr.utils.StringUtils;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Observable<ISSRResponse> searchRequestCall(SearchRequestRequest request) {
        return rx.Observable.create(subscriber -> {
            try {
//                ISSRResponse response = callWS(request, ISSRResponse.class, "/searchRequest");
                ISSRResponse response = mockCallSearchRequestResponse(request, ISSRResponse.class);
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
        prepareMockResponse(response);
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
        prepareMockResponse2(response);
        return (T) response;
    }

    private <T> T mockCallSearchRequestResponse(ISSRRequest request, Class<T> clazz) {
        String sRequest = StringUtils.requestToJson(request);
        Log.i(TAG, "Sending request: " + sRequest);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SearchRequestResponse response = new SearchRequestResponse();
        prepareMockResponse3(response);
        return (T) response;
    }

    private void prepareMockResponse(ISSRResponse response) {

        List<RequestState> states = new ArrayList<>();
        RequestState s0 = new RequestState("15.06.2020 - 21.12.2020", "uzatvoreny podpisany");
        RequestState s1 = new RequestState("31.04.2020 - 01.12.2020", "uhradeny");
        states.add(s1);
        RequestState s2 = new RequestState("18.05.2020 - 18.12.2020", "caka na uhradu");
        states.add(s2);
        RequestState s3 = new RequestState("05.05.2020 - 31.12.2020", "caka na poukaz");
        states.add(s3);

        DetailedRequest detail = new DetailedRequest();
        detail.setRequestId("ID1");
        detail.setState(s0);
        detail.setType("Dohoda");
        detail.setListOfPreviousStates(states);

        List<DetailedRequest> requests = new ArrayList<>();
        requests.add(detail);

        response.setCode(0);
        response.setDescription("OK");
        response.setListOfRequests(requests);
    }


    private void prepareMockResponse2(ISSRResponse response) {

        List<RequestState> states = new ArrayList<>();
        RequestState s0 = new RequestState("15.06.2020 - 21.12.2020", "uzatvoreny podpisany");
        RequestState s1 = new RequestState("31.04.2020 - 01.12.2020", "uhradeny");
        states.add(s1);
        RequestState s2 = new RequestState("18.05.2020 - 18.12.2020", "caka na uhradu");
        states.add(s2);
        RequestState s3 = new RequestState("05.05.2020 - 31.12.2020", "caka na poukaz");
        states.add(s3);

        DetailedRequest detail = new DetailedRequest();
        detail.setRequestId("ID1");
        detail.setState(s0);
        detail.setType("Dohoda");
        detail.setListOfPreviousStates(states);

        DetailedRequest detail2 = new DetailedRequest();
        detail2.setRequestId("ID2");
        detail2.setState(s0);
        detail2.setType("Ziadost");
        detail2.setListOfPreviousStates(states);

        List<DetailedRequest> requests = new ArrayList<>();
        requests.add(detail);
        requests.add(detail2);

        response.setCode(0);
        response.setDescription("OK");
        response.setListOfRequests(requests);
    }

    private void prepareMockResponse3(ISSRResponse response) {
        response.setCode(404);
        response.setDescription("Požiadavka nebola nájdená. Skúste skontrolovať parametre vyhľadávania.");
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
