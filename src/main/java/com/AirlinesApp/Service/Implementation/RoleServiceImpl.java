package com.AirlinesApp.Service.Implementation;

import com.AirlinesApp.Model.Role;
import com.AirlinesApp.Repository.RoleRepository;
import com.AirlinesApp.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    public List<Role> getAllRoles(){
        return roleRepository.getAllRoles();
    }
}
