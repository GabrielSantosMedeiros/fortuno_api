package com.example.fortuno_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fortuno_api.models.User;
import com.example.fortuno_api.models.Wallet;


public interface WalletRepository extends JpaRepository<Wallet, Long> {
    
    List<Wallet> findByOwner(User owner);
    
    Wallet findByNameAndOwner(String name, User owner);
}
