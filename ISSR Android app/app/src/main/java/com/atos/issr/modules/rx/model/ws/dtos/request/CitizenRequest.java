package com.atos.issr.modules.rx.model.ws.dtos.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Jarci on 3. 6. 2020.
 */

@XmlRootElement(
        name = "ISSRCitizenRequest"
)
public class CitizenRequest extends ISSRRequest {
    @XmlElement(
            name = "firstName",
            required = true
    )
    private String firstName;
    @XmlElement(
            name = "lastName",
            required = true
    )
    private String lastName;
    @XmlElement(
            name = "personalNo",
            required = true
    )
    private String personalNo;

    public CitizenRequest(String firstName, String lastName, String personalNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNo = personalNo;
    }

}
