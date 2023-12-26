package com.example.fortuno_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fortuno_api.dtos.wallet.WalletCreatedInfoDTO;
import com.example.fortuno_api.models.Wallet;
import com.example.fortuno_api.repositories.WalletRepository;

@Service
public class WalletService {
    
    @Autowired
    WalletRepository walletRepository;

    public WalletCreatedInfoDTO create(Wallet wallet) {
        walletRepository.save(wallet);
        return new WalletCreatedInfoDTO(
            wallet.getBalance(), 
            wallet.getName(), 
            wallet.getDescription(), 
            wallet.getOwner().getUsername()
        );
    }
}
