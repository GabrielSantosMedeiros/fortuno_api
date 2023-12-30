package com.example.fortuno_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fortuno_api.dtos.wallet.WalletPublicInfo;
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

    @GetMapping("/{username}")
    public ResponseEntity<Object> getAllByOwner(@PathVariable("username") String username) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(walletService.getWalletsByOwner(user));
    }

    @GetMapping("/{username}/{wallet}")
    public WalletPublicInfo getWalletByNameAndOwner(@PathVariable("username") String username, @PathVariable("wallet") String wallet) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.getUsername().equals(username)) throw new Exception("access denied: owner and user should be the same.");
        return walletService.getWalletByNameAndOwner(wallet, user);
    }

    @PostMapping
    public ResponseEntity<Object> save(@ModelAttribute Wallet wallet) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
        wallet.setOwner(user);
        return ResponseEntity.ok().body(walletService.create(wallet));
    }
}
