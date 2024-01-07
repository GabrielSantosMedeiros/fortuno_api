package com.example.fortuno_api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fortuno_api.dtos.wallet.WalletDTO;
import com.example.fortuno_api.models.User;
import com.example.fortuno_api.models.Wallet;
import com.example.fortuno_api.repositories.WalletRepository;

@Service
public class WalletService {
    
    @Autowired
    WalletRepository walletRepository;

    public WalletDTO getWalletByNameAndOwner(String walletName, User user) throws Exception {
        Wallet wallet = walletRepository.findByNameAndOwner(walletName, user);
        if(wallet==null) throw new Exception("Wallet not found.");
        return new WalletDTO(
            wallet.getBalance(),
            wallet.getName(),
            wallet.getDescription(),
            wallet.getOwner().getUsername(),
            wallet.getCreatedAt(),
            wallet.getLastModifiedAt()
        );
    }

    public List<WalletDTO> getWalletsByOwner(User user) {
        List<Wallet> wallets = walletRepository.findByOwner(user);
        List<WalletDTO> walletsDTO = new ArrayList<>();

        for(Wallet wallet : wallets) {
            walletsDTO.add(new WalletDTO(
                wallet.getBalance(), 
                wallet.getName(),
                wallet.getDescription(),
                wallet.getOwner().getUsername(), 
                wallet.getCreatedAt(),
                wallet.getLastModifiedAt()
            ));
        }
        return walletsDTO;
    }

    public WalletDTO create(Wallet wallet) {
        walletRepository.save(wallet);
        
        return new WalletDTO(
            wallet.getBalance(), 
            wallet.getName(), 
            wallet.getDescription(), 
            wallet.getOwner().getUsername(), 
            wallet.getCreatedAt(), 
            wallet.getLastModifiedAt()
        );
    }

    public WalletDTO modify(String walletName, User owner, Wallet newWalletInfo) throws Exception {
        Wallet wallet = walletRepository.findByNameAndOwner(walletName, owner);
        if(wallet==null) throw new Exception("wallet not found.");
        
        if(newWalletInfo.getBalance()!=null) wallet.setBalance(newWalletInfo.getBalance());
        if(!newWalletInfo.getName().isEmpty()) wallet.setName(newWalletInfo.getName());
        if(!newWalletInfo.getDescription().isEmpty()) wallet.setDescription(newWalletInfo.getDescription());

        walletRepository.save(wallet);
        return new WalletDTO(
            wallet.getBalance(), 
            wallet.getName(), 
            wallet.getDescription(), 
            wallet.getOwner().getUsername(), 
            wallet.getCreatedAt(), 
            wallet.getLastModifiedAt()
        );
    }

    public String delete(String walletName, User owner) {
        Wallet wallet = walletRepository.findByNameAndOwner(walletName, owner);
        if(wallet!=null) walletRepository.delete(wallet);
        return "The wallet has been deleted.";
    }
}
