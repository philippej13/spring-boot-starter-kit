package com.company.appli.repository;


import com.company.appli.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryCustom {
    Account findByEmail(String domaine, String email);
    Optional<Account> findById(String id);
    List<Account> findAllByDomaine(String domaine);
    Account insertAccount(Account account);
}
