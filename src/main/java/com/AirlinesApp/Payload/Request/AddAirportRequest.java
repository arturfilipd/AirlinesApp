package com.AirlinesApp.Payload.Request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Klasa żądania dodania lotniska
 */
public class AddAirportRequest {
    /**
     * Nazwa lotniska
     */
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
    /**
     * Kod lotniska
     */
    @NotBlank
    @Size(min = 3, max = 5)
    private String code;
    /**
     * Miasto
     */
    @NotBlank
    @Size(min = 2, max = 40)
    private String city;

    /**
     * Getter nazwy
     * @return name - nazwa
     */
    public String getName() {
        return name;
    }

    /**
     * Setter nazwy
     * @param name - nazwa
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter miasta
     * @return city - miasto
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter miasta
     * @param c - miasto
     */
    public void setCity(String c){   this.city = c; }

    /**
     * Getter kodu
     * @return code - kod
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter kodu
     * @param code - kod
     */
    public void setCode(String code) {
        this.code = code;
    }

}