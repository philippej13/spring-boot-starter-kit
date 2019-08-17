package com.company.appli.repository.elasticsearch;

import com.company.appli.model.Account;
import com.company.appli.repository.AccountRepositoryCustom;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccountElasticsearchRepository extends CrudRepository<Account, String>, AccountRepositoryCustom {

}
