package com.apriltechnologies.amadmin.repository;

import com.apriltechnologies.amadmin.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String>, AccountRepositoryCustom {

}
