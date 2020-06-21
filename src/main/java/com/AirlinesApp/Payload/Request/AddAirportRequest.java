package com.AirlinesApp.Payload.Request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddAirportRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(min = 3, max = 5)
    private String code;

    @NotBlank
    @Size(min = 2, max = 40)
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String c){   this.city = c; }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

}