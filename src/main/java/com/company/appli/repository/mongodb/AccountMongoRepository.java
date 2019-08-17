package com.company.appli.repository.mongodb;

import com.company.appli.model.Account;
import com.company.appli.repository.AccountRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountMongoRepository extends MongoRepository<Account, String>, AccountRepositoryCustom {

}
