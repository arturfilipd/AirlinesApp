package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.*;
import com.AirlinesApp.Payload.Request.ChangePasswordRequest;
import com.AirlinesApp.Payload.Request.LoginRequest;
import com.AirlinesApp.Payload.Request.SignupRequest;
import com.AirlinesApp.Payload.Response.JwtResponse;
import com.AirlinesApp.Payload.Response.MessageResponse;
import com.AirlinesApp.Repository.ClientRepository;
import com.AirlinesApp.Repository.PersonRepository;
import com.AirlinesApp.Repository.RoleRepository;
import com.AirlinesApp.Repository.UserRepository;
import com.AirlinesApp.Security.Services.UserDetailsImpl;
import com.AirlinesApp.Security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Klasa kontrolera autoryzacji, mapowanego pod adresem "/api/auth".
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PersonRepository people;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ClientRepository clients;

    /**
     * Mapowanie do logowania użytkownika
     * @param loginRequest Ciało zapytania
     *                     String username - login
     *                     String password - hasło
     * @return Wiadomość informująca o powodzeniu autoryzacji, token jwt w przypadku powodzenia
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


    /**
     * Mapowanie rejestracji użytkownika
     * @param signUpRequest Ciało zapytania
     *                      String username - login
     *                      String password - hasło
     *
     *                      String email - adres e-mail
     *                      String name - imię
     *                      String surname - nazwisko
     *                      String personalID - numer PESEL
     *                      String phoneNumber - telefon kontaktowy
     *
     * @return Odpowiedź informująca o rezultacie działania.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Person person = new Person(signUpRequest.name, signUpRequest.surname, signUpRequest.personalID, signUpRequest.phoneNumber);

        people.save(person);
        Set<String> strRoles = signUpRequest.getRole();
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

        user.setRoles(roles);
        user.setPersonID(person);
        userRepository.save(user);
        Client client = new Client();
        client.setUserId(user);
        clients.save(client);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    /**
     * Mapowanie zmiany hasła uźytkownika
     * @param req Ciało zapytania
     *            userId - ID użytkownika zgłaszającego zapytanie
     *            oldPassword - obecne hasło użytkownika
     *            newPassword - nowe hasło użytkownika
     * @param Authorization Token JWT uwierzytelniający użytkownika przesyłany w nagłówku
     * @return Odpowiedź informująca o rezultacie działania.
     */
    @Transactional
    @PostMapping("changePass")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('MANAGER')")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest req, @RequestHeader String Authorization){
        if(!userRepository.existsById(req.userId)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid user!"));
        }
        String tokenName = jwtUtils.getUserNameFromJwtToken(Authorization.substring(7));
        if(!tokenName.equals(userRepository.findOneById(req.userId).getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Unauthorized!"));
        }
        User user = userRepository.findOneById(req.userId);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), req.oldPassword));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        userRepository.changePassword(user.getId(), encoder.encode(req.newPassword));
        return ResponseEntity.ok(new MessageResponse("Password changed successfully!"));
    }

}