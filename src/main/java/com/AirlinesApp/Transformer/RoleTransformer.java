package com.AirlinesApp.Transformer;

import com.AirlinesApp.Model.Role;
import com.AirlinesApp.dto.RoleDto;
import org.springframework.beans.BeanUtils;

public class RoleTransformer {
    public static RoleDto convertToDto(Role role){
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(role, roleDto);
        return roleDto;
    }
    public static Role convertToEntity(RoleDto roleDto){
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        return role;
    }
}
