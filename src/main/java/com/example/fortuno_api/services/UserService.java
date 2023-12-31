package com.example.fortuno_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.fortuno_api.dtos.user.UserDTO;
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
    
    public UserDTO create(User user) {
        String passwordEncoded = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(passwordEncoded);
        userRepository.save(user);

        return new UserDTO(
            user.getUsername(),
            user.getEmail(),
            user.getCreatedAt(),
            user.getLastModifiedAt()
        );
    }

    public UserDTO getUser(String username) throws Exception {
        User user = (User) loadUserByUsername(username);
        if(user==null) throw new Exception("User not found!");
        return new UserDTO(
            user.getUsername(), 
            user.getEmail(), 
            user.getCreatedAt(), 
            user.getLastModifiedAt()
        );
    }

    public UserDTO modifyUser(String username, User newInfoUser) throws Exception {
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
        return new UserDTO(
            user.getUsername(), 
            user.getEmail(), 
            user.getCreatedAt(), 
            user.getLastModifiedAt()
        );
    }

    public String delete(String username) throws Exception {
        User user = (User) loadUserByUsername(username);
        if(user==null) throw new Exception("User not found!");
        userRepository.delete(user);
        return username + " has been deleted!";
    }
}
