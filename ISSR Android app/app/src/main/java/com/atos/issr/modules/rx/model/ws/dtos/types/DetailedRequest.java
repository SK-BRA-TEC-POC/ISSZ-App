package com.atos.issr.modules.rx.model.ws.dtos.types;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Jarci on 3. 6. 2020.
 */

public class DetailedRequest implements Serializable {
    @XmlElement(
            name = "requestId",
            required = true
    )
    private String requestId;
    @XmlElement(
            name = "description",
            required = true
    )
    private String description;
    @XmlElement(
            name = "type",
            required = true
    )
    private String type;
    @XmlElement(
            name = "actualState",
            required = true
    )
    private RequestState state;
    @XmlElement(
            name = "previousStates",
            required = true
    )
    private List<RequestState> listOfPreviousStates;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public RequestState getState() {
        return state;
    }

    public void setState(RequestState state) {
        this.state = state;
    }

    public List<RequestState> getListOfPreviousStates() {
        return listOfPreviousStates;
    }

    public void setListOfPreviousStates(List<RequestState> listOfPreviousStates) {
        this.listOfPreviousStates = listOfPreviousStates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
