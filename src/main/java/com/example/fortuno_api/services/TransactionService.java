package com.example.fortuno_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fortuno_api.repositories.TransactionRepository;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepository transactionRepository;
}
