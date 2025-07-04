package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepo;

    public Account insertAccount(Account account)
    {
        String testUser = account.getUsername();
        String testPass = account.getPassword();
        if(testUser == null || testUser.isBlank() || testPass == null || testPass.isBlank())
        {
            return null;
        }

        if(accountRepo.findByUsername(testUser) != null)
        {
            return null;
        }
        return accountRepo.save(account);
    }

    public boolean login(String user, String password)
    {
        Account exists = accountRepo.findByUsername(user);
        if(exists != null && exists.getPassword().equals(password))
        {
            return true;
        }
        return false;
    }

    public boolean existsByUser(String username)
    {
        return accountRepo.existsByUsername(username);
    }
}
