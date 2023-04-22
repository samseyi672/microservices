package com.activedge.service;

import com.activedge.dto.UserDto;
import com.activedge.model.User;

public interface UserService {
    User saveUser(UserDto user);
    String generateToken(String username) ;
    public void validateUser(String token) ;
}
