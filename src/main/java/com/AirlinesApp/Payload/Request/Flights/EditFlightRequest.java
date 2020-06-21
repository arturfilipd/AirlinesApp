package com.AirlinesApp.Payload.Request.Flights;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class EditFlightRequest {
    @NotBlank
    public Integer id;
    @NotBlank
    @DateTimeFormat
    public Date newStart;
    @NotBlank
    @DateTimeFormat
    public Date newEnd;
}
