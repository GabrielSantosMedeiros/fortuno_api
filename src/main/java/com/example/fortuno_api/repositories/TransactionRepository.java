package com.example.fortuno_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fortuno_api.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
