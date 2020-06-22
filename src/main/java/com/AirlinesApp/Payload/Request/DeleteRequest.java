package com.AirlinesApp.Payload.Request;

import javax.validation.constraints.NotBlank;

/**
 * Klasa żądania usunięcia żądania
 */
public class DeleteRequest {
    @NotBlank
    public Integer id;
}
