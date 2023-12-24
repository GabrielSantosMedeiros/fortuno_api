package com.example.fortuno_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fortuno_api.dtos.UserCreatedInfoDTO;
import com.example.fortuno_api.dtos.UserPublicInfoDTO;
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

        return new UserCreatedInfoDTO(
            user.getUsername(), 
            user.getEmail(), 
            user.getCreatedAt()
        );
    }

    public UserPublicInfoDTO modifyUser(String username, User newInfoUser) throws Exception {
        User user = (User) loadUserByUsername(username);
        if(user==null) throw new Exception("User not found.");

        if(newInfoUser.getUsername()!=null && !newInfoUser.getUsername().isEmpty()) {
            if(username.equals(newInfoUser.getUsername())) throw new Exception("New username should be different.");
            if(userRepository.existsByUsername(newInfoUser.getUsername())) throw new Exception("It's username is already in use.");

            user.setUsername(newInfoUser.getUsername());
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(newInfoUser.getPassword()!=null && !newInfoUser.getUsername().isEmpty()) {
            if(encoder.matches(newInfoUser.getPassword(), user.getPassword())) throw new Exception("New password should be different.");
            user.setPassword(encoder.encode(newInfoUser.getPassword()));
        }

        userRepository.save(user);
        return new UserPublicInfoDTO(
            user.getUsername(), 
            user.getEmail(), 
            user.getCreatedAt(), 
            user.getLastModifiedAt()
        );
    }
}
