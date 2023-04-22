package com.activedge.service;


import com.activedge.config.JwtService;
import com.activedge.dto.UserDto;
import com.activedge.model.User;
import com.activedge.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserAuthServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository ;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public User saveUser(UserDto user) {
        log.info("creating  the user {}",user.toString());
        return userRepository.save(User.builder().username(user.getUsername()).password(user.getPassword()).build()) ;
    }

    @Override
    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    @Override
    public void validateUser(final String token) {
        jwtService.validateToken(token);
    }
}
