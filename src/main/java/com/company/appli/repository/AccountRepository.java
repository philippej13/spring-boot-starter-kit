package com.company.appli.repository;

import com.company.appli.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String>, AccountRepositoryCustom {

}
