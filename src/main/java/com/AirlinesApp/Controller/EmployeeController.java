package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.*;
import com.AirlinesApp.Payload.Request.Employees.AddEmployeeRequest;
import com.AirlinesApp.Payload.Request.Employees.FireEmployeeRequest;
import com.AirlinesApp.Payload.Response.MessageResponse;
import com.AirlinesApp.Repository.EmployeeRepository;
import com.AirlinesApp.Repository.PersonRepository;
import com.AirlinesApp.Repository.RoleRepository;
import com.AirlinesApp.Repository.UserRepository;
import com.AirlinesApp.Transformer.EmployeeTransformer;
import com.AirlinesApp.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping()
public class EmployeeController {
    @Autowired
    EmployeeRepository repository;
    @Autowired
    UserRepository users;
    @Autowired
    PersonRepository people;
    @Autowired
    RoleRepository roleRepository;
    @GetMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = repository.getAllEmployees();
        return employees.stream().map(EmployeeTransformer::convertToDto).collect(Collectors.toList());
    }

    @PostMapping("/addEmployee")
    @PreAuthorize("hasRole('Manager')")
    public ResponseEntity<?>addEmployee(@Valid @RequestBody AddEmployeeRequest req){
        if(users.existsByEmail(req.eMail)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: eMail is already taken!"));
        }
        String alphabet = "qwertyuiopasdfghjklzxcvbnm";
        String pass = "";
        Random rand = new Random();
        for(int i=0;i<8;i++){
            pass+=alphabet.charAt(rand.nextInt(alphabet.length()));
        }

        Set<String> strRoles = req.role;
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "manager":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_MANAGER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "employee":
                        Role modRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        Person p = people.save(new Person(req.name, req.surname, req.personalID, req.phoneNumber));
        repository.save(new Employee(p, req.salary, new Date(), req.position));
        User user = new User(req.eMail, req.eMail, pass);
        user.setPersonID(p);
        user.setRoles(roles);
        users.save(user);
        return ResponseEntity.ok(new MessageResponse("Employee added successfully!"));
    }

    @PostMapping("/fireEmployee")
    @PreAuthorize("hasRole('Manager')")
    @Transactional
    public ResponseEntity<?>fireEmployee(@Valid @RequestBody FireEmployeeRequest req){
        if(!repository.existsById(req.id)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: no such Employee!"));
        }
        repository.removeEmployee(new Date(), req.id);
        Employee e = repository.getOne(req.id);
        Person p = e.getPersonID();
        User u = users.findOneByPersonID(e.getPersonID());
        users.delete(u);
        return ResponseEntity.ok(new MessageResponse("Employee fired successfully!"));

    }
}
