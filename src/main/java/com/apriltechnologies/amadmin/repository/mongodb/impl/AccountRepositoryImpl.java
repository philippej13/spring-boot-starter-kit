package com.apriltechnologies.amadmin.repository.mongodb.impl;

import com.apriltechnologies.amadmin.model.Account;
import com.apriltechnologies.amadmin.repository.AccountRepositoryCustom;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;


public class AccountRepositoryImpl implements AccountRepositoryCustom {

    MongoTemplate mongoTemplate;

    public AccountRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Account> findAllByDomaine(String domaine) {
        Query query = new Query();
        query.addCriteria(Criteria.where("domaine").is(domaine));
        return mongoTemplate.find(query, Account.class);
    }

    public Account findByEmail (String domaine, String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email).and("domaine").is(domaine));
        return mongoTemplate.findOne(query, Account.class);
    }
}
