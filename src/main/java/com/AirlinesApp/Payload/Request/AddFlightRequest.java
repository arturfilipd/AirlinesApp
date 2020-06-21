package com.AirlinesApp.Payload.Request;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class AddFlightRequest {
    @NotBlank
    @DateTimeFormat
    public Date starts;

    @NotBlank
    @DateTimeFormat
    public Date ends;

    @NotBlank
    public Integer destID;
    @NotBlank
    public Integer sourceID;

    @NotBlank
    public Integer planeID;

    @NotBlank
    public Long priceEco;
    @NotBlank
    public Long priceBuis;



}