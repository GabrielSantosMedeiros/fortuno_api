package com.example.fortuno_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fortuno_api.models.User;
import com.example.fortuno_api.models.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    
    List<Wallet> findByOwner(User owner);
    
    Wallet findByNameAndOwner(String name, User owner);
}
