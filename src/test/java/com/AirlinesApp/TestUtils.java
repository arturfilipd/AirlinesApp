package com.AirlinesApp;

import com.AirlinesApp.Controller.AuthController;
import com.AirlinesApp.Payload.Request.LoginRequest;
import com.AirlinesApp.Payload.Response.JwtResponse;
import com.AirlinesApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TestUtils {

    @Autowired
    UserRepository users;

    @Autowired
    AuthController auth;

    public String getUserToken(String userName, String password){

        LoginRequest req = new LoginRequest();
        req.setPassword(password);
        req.setUsername(userName);
        ResponseEntity<?> resp = auth.authenticateUser(req);
        System.out.println();
        JwtResponse jwtR = (JwtResponse) resp.getBody();
        if(jwtR.getAccessToken() != null)
            return ("Bearer " + jwtR.getAccessToken());
        return "";
    }
}
