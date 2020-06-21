package com.AirlinesApp.Service.Implementation;

import com.AirlinesApp.Model.User;
import com.AirlinesApp.Repository.UserRepository;
import com.AirlinesApp.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }
}
