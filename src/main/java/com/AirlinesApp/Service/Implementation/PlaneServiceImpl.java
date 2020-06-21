package com.AirlinesApp.Service.Implementation;

import com.AirlinesApp.Model.Plane;
import com.AirlinesApp.Repository.PlaneRepository;
import com.AirlinesApp.Service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository planeRepository;
    public List<Plane> getAllPlanes(){
        return planeRepository.getAllPlanes();
    }
}
