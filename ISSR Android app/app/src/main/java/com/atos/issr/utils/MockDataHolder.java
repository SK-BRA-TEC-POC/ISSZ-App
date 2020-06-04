package com.atos.issr.utils;

import com.atos.issr.modules.rx.model.ws.dtos.response.CitizenResponse;
import com.atos.issr.modules.rx.model.ws.dtos.response.ISSRResponse;
import com.atos.issr.modules.rx.model.ws.dtos.response.LegalPersonResponse;
import com.atos.issr.modules.rx.model.ws.dtos.response.SearchRequestResponse;
import com.atos.issr.modules.rx.model.ws.dtos.types.DetailedRequest;
import com.atos.issr.modules.rx.model.ws.dtos.types.RequestState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jarci on 4. 6. 2020.
 */

public class MockDataHolder {
    private static CitizenResponse citizen1;
    private static CitizenResponse citizen2;
    private static LegalPersonResponse legalPerson;
    private static SearchRequestResponse searchRequest;
    private static ISSRResponse noDataResponse;

    public static CitizenResponse getCitizen1() {

        if (citizen1 == null) {
            citizen1 = new CitizenResponse();
            List<RequestState> states = new ArrayList<>();
            RequestState s0 = new RequestState("13.03.2019 - 21.04.2020", "uzatvorený podpísaný");
            states.add(new RequestState("13.3.2020-31.03.2020", " uhradený"));
            states.add(new RequestState("01.04.2020-21.04.2020", "čaká na výplatu"));

            DetailedRequest detail = new DetailedRequest();
            detail.setRequestId("20/51/54E/501");
            detail.setState(s0);
            detail.setType("Dohoda");
            detail.setDescription("§54e Dohoda Opatrenie4 ŠR Občan");
            detail.setListOfPreviousStates(states);

            List<DetailedRequest> requests = new ArrayList<>();
            requests.add(detail);

            citizen1.setCode(0);
            citizen1.setDescription("OK");
            citizen1.setListOfRequests(requests);
        }
        return citizen1;
    }

    public static CitizenResponse getCitizen2() {

        if (citizen2 == null) {
            citizen2 = new CitizenResponse();
            List<DetailedRequest> requests = new ArrayList<>();

            List<RequestState> states = new ArrayList<>();
            RequestState s0 = new RequestState("04.11.2019 - 03.11.2020", "uzatvorený podpísaný");
            states.add(new RequestState("04.11.2019 - 30.11.2019", "uhradený"));
            states.add(new RequestState("01.12.2019 - 31.12.2019", "uhradený"));
            states.add(new RequestState("01.01.2020 - 31.01.2020", "uhradený"));
            states.add(new RequestState("01.02.2020 - 29.02.2020", "uhradený"));
            states.add(new RequestState("01.03.2020 - 31.03.2020", "uhradený "));
            states.add(new RequestState("01.04.2020 - 30.04.2020", "čaká na úhradu"));
            states.add(new RequestState("01.05.2020 - 31.05.2020", "čaká na úhradu"));
            states.add(new RequestState("01.06.2020 - 30.06.2020", "čaká na úhradu"));
            states.add(new RequestState("01.07.2020 - 31.07.2020", "čaká na úhradu"));
            states.add(new RequestState("01.08.2020 - 31.08.2020", "čaká na úhradu"));
            states.add(new RequestState("01.09.2020 - 30.09.2020", "čaká na úhradu"));
            states.add(new RequestState("01.10.2020 - 31.10.2020", "čaká na úhradu"));
            states.add(new RequestState("01.11.2020 - 04.11.2020", "čaká na úhradu"));

            DetailedRequest detail = new DetailedRequest();
            detail.setRequestId("20/51/54E/501");
            detail.setState(s0);
            detail.setType("Dohoda");
            detail.setDescription("§53a Dohoda - Príspevok pre občana - ŠR (novela máj 2018)");
            detail.setListOfPreviousStates(states);

            requests.add(detail);

            List<RequestState> states2 = new ArrayList<>();
            RequestState s1 = new RequestState("13.03.2019 - 21.04.2020", "prijatý");

            DetailedRequest detail1 = new DetailedRequest();
            detail1.setRequestId(" 2020/84723");
            detail1.setState(s1);
            detail1.setType("Žiadosť");
            detail1.setDescription("§53a Žiadosť o príspevok pre občana");
            detail1.setListOfPreviousStates(states2);

            requests.add(detail1);

            citizen2.setCode(0);
            citizen2.setDescription("OK");
            citizen2.setListOfRequests(requests);
        }
        return citizen2;
    }

    public static LegalPersonResponse getLegalPerson() {

        if (legalPerson == null) {
            legalPerson = new LegalPersonResponse();
            List<DetailedRequest> requests = new ArrayList<>();

            List<RequestState> states2 = new ArrayList<>();
            RequestState s1 = new RequestState("13.03.2019 - 21.04.2020", "prijatý");

            DetailedRequest detail1 = new DetailedRequest();
            detail1.setRequestId("2020/122563");
            detail1.setState(s1);
            detail1.setType("Žiadosť");
            detail1.setDescription("§54e Žiadosť o príspevok na podporu udržania pracovných miest");
            detail1.setListOfPreviousStates(states2);

            requests.add(detail1);

            List<RequestState> states = new ArrayList<>();
            RequestState s0 = new RequestState("13.03.2020 - 31.12.2020", "uzatvorený podpísaný");
            states.add(new RequestState("13.3.2020-31.03.2020", "uhradený"));
            states.add(new RequestState("01.04.2020-30.04.2020", "čaká na úhradu"));
            states.add(new RequestState("01.05.2020-31.12.2020", "čaká na poukaz"));

            DetailedRequest detail = new DetailedRequest();
            detail.setRequestId("20/53/54E/1369");
            detail.setState(s0);
            detail.setType("Dohoda");
            detail.setDescription("§54e Dohoda Opatrenie1 ŠR");
            detail.setListOfPreviousStates(states);

            requests.add(detail);
            legalPerson.setCode(0);
            legalPerson.setDescription("OK");
            legalPerson.setListOfRequests(requests);
        }

        return legalPerson;
    }

    public static SearchRequestResponse getSearchRequest() {
        if (searchRequest == null) {
            searchRequest = new SearchRequestResponse();
            List<DetailedRequest> requests = new ArrayList<>();

            List<RequestState> states = new ArrayList<>();
            RequestState s0 = new RequestState("13.03.2020 - 31.12.2020", "uzatvorený podpísaný");
            states.add(new RequestState("13.3.2020-31.03.2020", "uhradený"));
            states.add(new RequestState("01.04.2020-30.04.2020", "čaká na úhradu"));
            states.add(new RequestState("01.05.2020-31.12.2020", "čaká na poukaz"));

            DetailedRequest detail = new DetailedRequest();
            detail.setRequestId("20/53/54E/1369");
            detail.setState(s0);
            detail.setType("Dohoda");
            detail.setDescription("§54e Dohoda Opatrenie1 ŠR");
            detail.setListOfPreviousStates(states);

            requests.add(detail);
            searchRequest.setCode(0);
            searchRequest.setDescription("OK");
            searchRequest.setListOfRequests(requests);
        }

        return searchRequest;
    }

    public static ISSRResponse getNoData() {
        if (noDataResponse == null) {
            noDataResponse = new ISSRResponse();
            noDataResponse.setCode(404);
            noDataResponse.setDescription("Požiadavka nebola nájdená. Skúste skontrolovať parametre vyhľadávania.");
        }
        return noDataResponse;
    }
}
