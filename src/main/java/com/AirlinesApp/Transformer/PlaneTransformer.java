package com.AirlinesApp.Transformer;

import com.AirlinesApp.Model.Plane;
import com.AirlinesApp.dto.PlaneDto;
import org.springframework.beans.BeanUtils;

public class PlaneTransformer {
    public static PlaneDto convertToDto(Plane plane){
        PlaneDto planeDto = new PlaneDto();
        BeanUtils.copyProperties(plane, planeDto);
        return planeDto;
    }
}
