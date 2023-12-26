package com.example.fortuno_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fortuno_api.models.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    
}
