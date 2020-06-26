package com.AirlinesApp.Payload.Request.Flights;

import com.AirlinesApp.Model.Flight;

import javax.validation.constraints.NotBlank;

public class GetFreeSeatsRequest {
    @NotBlank
    public Integer flightId;
    @NotBlank
    public String className;
}

