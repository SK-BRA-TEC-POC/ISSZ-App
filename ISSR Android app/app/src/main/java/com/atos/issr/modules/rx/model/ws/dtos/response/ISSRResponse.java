package com.atos.issr.modules.rx.model.ws.dtos.response;


import com.atos.issr.modules.rx.model.ws.dtos.types.DetailedRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by a605053 on 12. 1. 2018.
 */

public class ISSRResponse implements Serializable {
    @XmlElement(
            name = "code",
            required = true
    )
    private int code;
    @XmlElement(
            name = "description",
            required = true
    )
    private String description;

    @XmlElement(
            name = "requests",
            required = true
    )
    private List<DetailedRequest> listOfRequests;


    public ISSRResponse() {
        this.code = -273;
        this.description = "Testovacia hlaska";
        this.listOfRequests = new ArrayList<>();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DetailedRequest> getListOfRequests() {
        return listOfRequests;
    }

    public void setListOfRequests(List<DetailedRequest> listOfRequests) {
        this.listOfRequests = listOfRequests;
    }
}
