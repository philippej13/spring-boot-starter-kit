package com.company.appli.repository;


import com.company.appli.model.Account;

import java.util.List;

public interface AccountRepositoryCustom {
    Account findByEmail(String domaine, String email);
    public List<Account> findAllByDomaine(String domaine);
}
