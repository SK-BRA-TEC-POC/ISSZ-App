package com.atos.issr.modules.rx.model.ws.dtos.types;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Jarci on 3. 6. 2020.
 */
@XmlRootElement(
        name = "ProcessPeriodType"
)
public class RequestState implements Serializable {
    @XmlElement(
            name = "date",
            required = true
    )
    private String date;
    @XmlElement(
            name = "state",
            required = true
    )    private String state;

    public RequestState() {
    }

    public RequestState(String date, String state) {
        this.date = date;
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
