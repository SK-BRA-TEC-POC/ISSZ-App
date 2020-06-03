package com.atos.issr.modules.rx.model.ws.dtos.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Jarci on 3. 6. 2020.
 */

@XmlRootElement(
        name = "ISSRLegalPersonRequest"
)
public class LegalPersonRequest extends RequestWithCovidType {
    @XmlElement(
            name = "legalPersonId",
            required = true
    )
    private String legalPersonId;

    public LegalPersonRequest(int covidTypeRequest, String legalPersonId) {
        super(covidTypeRequest);
        this.legalPersonId = legalPersonId;
    }
}
