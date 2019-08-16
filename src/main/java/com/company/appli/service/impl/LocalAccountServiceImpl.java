package com.company.appli.service.impl;

import com.company.appli.model.Account;
import com.company.appli.repository.AccountRepository;
import com.company.appli.service.LocalAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Qualifier("localService")
public class LocalAccountServiceImpl implements LocalAccountService {

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
