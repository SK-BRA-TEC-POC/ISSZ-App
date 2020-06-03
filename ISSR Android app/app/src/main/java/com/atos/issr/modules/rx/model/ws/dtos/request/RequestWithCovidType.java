package com.atos.issr.modules.rx.model.ws.dtos.request;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Jarci on 3. 6. 2020.
 */

public abstract class RequestWithCovidType extends ISSRRequest {
    @XmlElement(
            name = "covidTypeRequest",
            required = true
    )
    private int covidTypeRequest;

    public RequestWithCovidType(int covidTypeRequest) {
        this.covidTypeRequest = covidTypeRequest;
    }
}
