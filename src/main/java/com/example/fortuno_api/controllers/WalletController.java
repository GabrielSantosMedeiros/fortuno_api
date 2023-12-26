package com.example.fortuno_api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.fortuno_api.models.User;
import com.example.fortuno_api.models.Wallet;
import com.example.fortuno_api.services.WalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/wallets")
public class WalletController {
    
    @Autowired
    WalletService walletService;

    @PostMapping
    public ResponseEntity<Object> save(@ModelAttribute Wallet wallet) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
        wallet.setOwner((User) auth.getPrincipal());
        return ResponseEntity.ok().body(walletService.create(wallet));
    }
}
