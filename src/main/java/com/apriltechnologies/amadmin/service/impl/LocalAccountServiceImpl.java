package com.apriltechnologies.amadmin.service.impl;

import com.apriltechnologies.amadmin.model.Account;
import com.apriltechnologies.amadmin.repository.AccountRepository;
import com.apriltechnologies.amadmin.repository.AccountRepositoryCustom;
import com.apriltechnologies.amadmin.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Qualifier("localService")
public class LocalAccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    @Autowired
    public LocalAccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String createAccount(Account account) {
        Account result = accountRepository.save(account);
        return result.getId();
    }

    @Override
    public boolean deleteAccount(String domaine, String email) {
        return false;
    }

    @Override
    public boolean existsAccount(String domaine, String email) {
        return false;
    }

    @Override
    public Account findByEmail(String domaine, String email) {
        return accountRepository.findByEmail(domaine, email);
    }

    @Override
    public List<Account> findAllByDomaine(String domaine) {
        return accountRepository.findAllByDomaine(domaine);
    }

    @Override
    public Account updateAccount(String domaine, String email, String name, String firstName) {
        return null;
    }

    @Override
    public Optional<Account> findById(String id) {
        return accountRepository.findById(id);
    }
}
