package com.example.fortuno_api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fortuno_api.dtos.wallet.WalletCreatedInfoDTO;
import com.example.fortuno_api.dtos.wallet.WalletPublicInfo;
import com.example.fortuno_api.models.User;
import com.example.fortuno_api.models.Wallet;
import com.example.fortuno_api.repositories.WalletRepository;

@Service
public class WalletService {
    
    @Autowired
    WalletRepository walletRepository;

    public WalletPublicInfo getWalletByNameAndOwner(String name, User user) {
        Wallet wallet = walletRepository.findByNameAndOwner(name, user);
        return new WalletPublicInfo(
            wallet.getBalance(),
            wallet.getName(),
            wallet.getOwner().getUsername(),
            wallet.getCreatedAt()
        );
    }

    public List<WalletPublicInfo> getWalletsByOwner(User user) {
        List<Wallet> wallets = walletRepository.findByOwner(user);
        List<WalletPublicInfo> walletsDTO = new ArrayList<>();

        for(Wallet wallet : wallets) {
            walletsDTO.add(new WalletPublicInfo(
                wallet.getBalance(), 
                wallet.getName(), 
                wallet.getOwner().getUsername(), 
                wallet.getCreatedAt()
            ));
        }
        return walletsDTO;
    }

    public WalletCreatedInfoDTO create(Wallet wallet) {
        walletRepository.save(wallet);
        
        return new WalletCreatedInfoDTO(
            wallet.getBalance(), 
            wallet.getName(), 
            wallet.getDescription(), 
            wallet.getOwner().getUsername(), 
            wallet.getCreatedAt(), 
            wallet.getLastModifiedAt()
        );
    }
}
