package com.AirlinesApp.Payload.Request;

import javax.validation.constraints.NotBlank;

public class DeleteRequest {
    @NotBlank
    public Integer id;
}
