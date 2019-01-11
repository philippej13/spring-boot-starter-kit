package com.company.appli.service;

import com.company.appli.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    String createAccount(Account account);
    boolean deleteAccount(String domaine,String email);
    boolean existsAccount(String domaine,String email);
    Account updateAccount(String domaine, String email, String name, String firstName);
    Optional<Account> findById(String id);
    Account findByEmail(String domaine, String email);
    List<Account> findAllByDomaine(String domaine);
}
