package com.atos.issr.modules.rx.model.ws.dtos.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Jarci on 3. 6. 2020.
 */
@XmlRootElement(
        name = "ISSRSearchRequestRequest"
)
public class SearchRequestRequest extends ISSRRequest {
    @XmlElement(
            name = "requestId",
            required = true
    )
    private String requestId;

    public SearchRequestRequest(String requestId) {
        this.requestId = requestId;
    }
}
