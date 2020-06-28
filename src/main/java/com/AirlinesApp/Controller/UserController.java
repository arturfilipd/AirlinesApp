package com.AirlinesApp.Controller;

import com.AirlinesApp.Exception.ResourceNotFoundException;
import com.AirlinesApp.Model.User;
import com.AirlinesApp.Repository.UserRepository;
import com.AirlinesApp.Security.CurrentUser;
import com.AirlinesApp.Security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Klasa mapująca usera
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    /**
     * Metoda zwracająca widok Thymeleaf
     * @return Model widoku thymeleaf
     */
    @RequestMapping(value = {"/thyme"})
    @ResponseBody
    public ModelAndView userlistePage() {
        ModelAndView model = new ModelAndView();
        List<User> users = userRepository.getAllUsers();
        model.addObject("userCount", users.size());
        System.out.println("SIZE = " + users.size());
        System.out.println("NAME = " + users.get(users.size()-1).getUsername());
        model.addObject("lastUser", users.get(users.size()-1).getUsername());
        model.setViewName("index");
        return model;
    }

}