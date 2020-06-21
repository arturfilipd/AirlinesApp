package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.Role;
import com.AirlinesApp.Service.RoleService;
import com.AirlinesApp.Transformer.RoleTransformer;
import com.AirlinesApp.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping()
public class RoleController{
    private final RoleService roleService;
    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleDto> getRoles(){
        List<Role> roles = roleService.getAllRoles();
        return roles.stream().map(RoleTransformer::convertToDto).collect(Collectors.toList());
    }
}
