package com.AirlinesApp.dto;

import com.AirlinesApp.Model.Airport;
import lombok.Data;
import lombok.Setter;

@Data
public class AirportDto {
        @Setter private Integer id;
        @Setter private String airportName;
        @Setter private String code;
        @Setter private String cityName;

        public AirportDto(){}

        public AirportDto(int id, String airportName, String cityCode, String cityName){
                this.id = id;
                this.airportName = airportName;
                this.code = cityCode;
                this.cityName = cityName;
        }
}