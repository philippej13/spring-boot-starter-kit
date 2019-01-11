package com.apriltechnologies.amadmin.repository;


import com.apriltechnologies.amadmin.model.Account;

import java.util.List;

public interface AccountRepositoryCustom {
    Account findByEmail(String domaine, String email);
    public List<Account> findAllByDomaine(String domaine);
}
