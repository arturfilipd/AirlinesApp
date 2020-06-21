package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Plane;
import com.AirlinesApp.Service.PlaneService;
import com.AirlinesApp.Transformer.PlaneTransformer;
import com.AirlinesApp.dto.PlaneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping()
public class PlaneController{
    private final PlaneService planeService;
    @GetMapping("/planes")
    @ResponseStatus(HttpStatus.OK)
    public List<PlaneDto> getPlanes(){
        List<Plane> planes = planeService.getAllPlanes();
        return planes.stream().map(PlaneTransformer::convertToDto).collect(Collectors.toList());
    }
}
