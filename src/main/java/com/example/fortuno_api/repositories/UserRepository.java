package com.example.fortuno_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.fortuno_api.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    UserDetails findByUsername(String username);

    boolean existsByUsername(String username);
}
