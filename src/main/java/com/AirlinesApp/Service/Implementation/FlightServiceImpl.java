package com.AirlinesApp.Service.Implementation;

import com.AirlinesApp.Model.Flight;
import com.AirlinesApp.Repository.FlightRepository;
import com.AirlinesApp.Service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    public List<Flight> getAllFlights(){
        return flightRepository.getAllFlights();
    }
}
