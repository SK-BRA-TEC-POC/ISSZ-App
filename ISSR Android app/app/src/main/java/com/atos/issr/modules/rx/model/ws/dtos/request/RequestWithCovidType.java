package com.atos.issr.modules.rx.model.ws.dtos.request;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Jarci on 3. 6. 2020.
 */

public abstract class RequestWithCovidType extends ISSRRequest {
    @XmlElement(
            name = "requestType",
            required = true
    )
    private int requestType;

    public RequestWithCovidType(int requestType) {
        this.requestType = requestType;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }
}
