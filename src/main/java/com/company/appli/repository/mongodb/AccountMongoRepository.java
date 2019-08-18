package com.company.appli.repository.mongodb;

import com.company.appli.model.Account;
import com.company.appli.repository.AccountRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccountMongoRepository extends CrudRepository<Account, String>, AccountRepositoryCustom {

}
