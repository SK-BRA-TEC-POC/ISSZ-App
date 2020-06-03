package com.atos.issr.modules.rx.model.ws.dtos.response;


import javax.xml.bind.annotation.XmlElement;

/**
 * Created by a605053 on 12. 1. 2018.
 */

public class ISSRResponse {
    @XmlElement(
            name = "code",
            required = true
    )
    private int code;
    @XmlElement(
            name = "description",
            required = true
    )    private String description;

    public ISSRResponse() {
        this.code = 0;
        this.description = "Testovacia hlaska";
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
}
