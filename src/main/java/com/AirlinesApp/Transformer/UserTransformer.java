package com.AirlinesApp.Transformer;

import com.AirlinesApp.Model.User;
import com.AirlinesApp.dto.UserDto;
import org.springframework.beans.BeanUtils;

public class UserTransformer {
    public static UserDto convertToDto(User user){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }
    public static User convertToEntity(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
}
