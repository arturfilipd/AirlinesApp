package com.AirlinesApp.Service.Implementation;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.Repository.AirportRepository;
import com.AirlinesApp.Service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;
    public List<Airport> getAllAirports(){
        return airportRepository.getAllAirports();
    }
}
