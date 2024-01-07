package com.example.fortuno_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fortuno_api.dtos.wallet.WalletDTO;
import com.example.fortuno_api.models.User;
import com.example.fortuno_api.models.Wallet;
import com.example.fortuno_api.services.UserService;
import com.example.fortuno_api.services.WalletService;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    UserService userService;
    
    @Autowired
    WalletService walletService;

    @GetMapping
    public ResponseEntity<Object> getAllByOwner() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(walletService.getWalletsByOwner(user));
    }

    @GetMapping("/{wallet-name}")
    public WalletDTO getWalletByNameAndOwner(@PathVariable("wallet-name") String walletName) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return walletService.getWalletByNameAndOwner(walletName, user);
    }

    @PostMapping
    public ResponseEntity<Object> save(@ModelAttribute Wallet wallet) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
        wallet.setOwner(user);
        return ResponseEntity.ok().body(walletService.create(wallet));
    }

    @PutMapping("/{wallet-name}")
    public ResponseEntity<Object> modify(@PathVariable("wallet-name") String walletName, @ModelAttribute Wallet newWalletInfo) 
            throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(walletService.modify(walletName, user, newWalletInfo));
    }

    @DeleteMapping("/{wallet-name}")
    public ResponseEntity<Object> delete(@PathVariable("wallet-name") String walletName) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(walletService.delete(walletName, user));
    }
}
    