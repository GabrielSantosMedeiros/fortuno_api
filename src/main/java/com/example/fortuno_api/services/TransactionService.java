package com.example.fortuno_api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fortuno_api.dtos.transaction.TransactionDTO;
import com.example.fortuno_api.models.Transaction;
import com.example.fortuno_api.repositories.TransactionRepository;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepository transactionRepository;
    
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionDTO> transactionsDTO = new ArrayList<>();

        for(Transaction transaction : transactions) {
            transactionsDTO.add(new TransactionDTO(
                transaction.getAmount(),
                transaction.getOwner().getUsername(),
                transaction.getWallet().getName(),
                transaction.getType().toString(),
                transaction.getCreatedAt(),
                transaction.getLastModifiedAt()
            ));
        }
        return transactionsDTO;
    }

    public TransactionDTO create(Transaction transaction) {
        transactionRepository.save(transaction);
        return new TransactionDTO(
            transaction.getAmount(), 
            transaction.getOwner().getUsername(),
            transaction.getWallet().getName(), 
            transaction.getType().toString(), 
            transaction.getCreatedAt(), 
            transaction.getLastModifiedAt()
        );
    }
}
