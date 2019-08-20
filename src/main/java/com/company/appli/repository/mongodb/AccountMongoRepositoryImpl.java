package com.company.appli.repository.mongodb;

import com.company.appli.model.Account;
import com.company.appli.repository.AccountRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@Qualifier("mongoRepository")
@ConditionalOnProperty(name = "repository.mongo.active", havingValue = "true")
public class AccountMongoRepositoryImpl implements AccountRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    public AccountMongoRepositoryImpl() {}

    @Override
    public Account insertAccount(Account account) {
        return mongoTemplate.save(account);
    }

    public List<Account> findAllByDomaine(String domaine) {
        Query query = new Query();
        query.addCriteria(Criteria.where("domaine").is(domaine));
        return mongoTemplate.find(query, Account.class);
    }

    @Cacheable("accounts")
    public Account findByEmail(String domaine, String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email).and("domaine").is(domaine));
        return mongoTemplate.findOne(query, Account.class);
    }

    @Override
    public Optional<Account> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, Account.class));
    }
}
