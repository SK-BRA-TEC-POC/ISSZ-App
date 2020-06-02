package com.atos.issr.modules.rx.model.ws.dtos.response;


/**
 * Created by a605053 on 12. 1. 2018.
 */

public class UpsvarResponse {
    private String code;
    private String description;

    public UpsvarResponse() {
        this.code = "";
        this.description = "Testovacia hlaska";
    }

//    public UpsvarResponse(MDsaResponseStatus status) {
//        this.code = status.getCode();
//        this.description = status.getDescription();
//    }
//
//    public UpsvarResponse(MDsaResponse response) {
//        this.code = response.getStatus().getCode();
//        this.description = response.getStatus().getDescription();
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
