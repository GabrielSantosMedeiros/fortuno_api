package com.example.fortuno_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fortuno_api.dtos.UserCreatedInfoDTO;
import com.example.fortuno_api.models.User;
import com.example.fortuno_api.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public List<User> loadAllUsers() {
        return userRepository.findAll();
    }
    
    public UserCreatedInfoDTO create(User user) {
        String passwordEncoded = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(passwordEncoded);

        userRepository.save(user);
        UserCreatedInfoDTO jsonInfo = new UserCreatedInfoDTO(
            user.getUsername(), 
            user.getEmail(), 
            user.getPassword(),
            user.getCreatedAt()
        );
        return jsonInfo;
    }
}