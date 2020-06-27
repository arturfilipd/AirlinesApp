package com.AirlinesApp.Payload.Response;

import lombok.Getter;
import lombok.Setter;

public class NewEmployeeResponse {
    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String password;

    public NewEmployeeResponse(String l, String p) {
        this.login = l;
        this.password = p;
    }

}