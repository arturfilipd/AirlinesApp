package com.AirlinesApp.Controller;

import com.AirlinesApp.Model.User;
import com.AirlinesApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class FacebookConnectionSignup implements ConnectionSignUp {
    @Autowired
    UserRepository userRepository;
    @Override
    public String execute(Connection<?> connection){
        User user = new User();
        user.setUsername(connection.getDisplayName());
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String pass = "";
        Random rand = new Random();
        for(int i=0;i<8;i++){
            pass+=alphabet.charAt(rand.nextInt(alphabet.length()));
        }
        user.setPassword(pass);
        userRepository.save(user);
        return user.getUsername();
    }
}
