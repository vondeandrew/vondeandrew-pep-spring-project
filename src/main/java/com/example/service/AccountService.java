package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository acountRepo;

    public Account insertAccount(Account newAccount)
    {
        return null;
    }

    public Account login(String user, String password)
    {
        return null;
    }

    public boolean existsById(int id)
    {
        return false;
    }
}
