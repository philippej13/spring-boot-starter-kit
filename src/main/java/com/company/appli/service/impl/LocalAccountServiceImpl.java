package com.company.appli.service.impl;

import com.company.appli.model.Account;
import com.company.appli.repository.AccountRepositoryCustom;
import com.company.appli.repository.elasticsearch.AccountElasticsearchRepository;
import com.company.appli.repository.elasticsearch.AccountElasticsearchRepositoryImpl;
import com.company.appli.repository.mongodb.AccountMongoRepository;
import com.company.appli.service.LocalAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Qualifier("localService")
public class LocalAccountServiceImpl implements LocalAccountService {

    AccountRepositoryCustom accountMongoRepository;
    AccountRepositoryCustom accountElasticsearchRepository;

    @Autowired
    public LocalAccountServiceImpl(@Qualifier("mongoRepository") AccountRepositoryCustom accountMongoRepository, @Qualifier("elasticsearchRepository") AccountRepositoryCustom accountElasticsearchRepository) {
        this.accountMongoRepository = accountMongoRepository;
        this.accountElasticsearchRepository = accountElasticsearchRepository;
    }

    @Override
    public String createAccount(Account account) {
        //Création dans Mongo
        log.info("Insertion dans Mongo");
        Account result = accountMongoRepository.insertAccount(account);
        //Création dans ES
        log.info("Insertion dans Elasticsearch");
        accountElasticsearchRepository.insertAccount(account);
        return result.getId();
    }

    @Override
    public boolean deleteAccount(String domaine, String email) {
        return false;
    }

    @Override
    public boolean existsAccount(String domaine, String email) {
        return false;
    }

    @Override
    public Account findByEmail(String domaine, String email) {
        return accountMongoRepository.findByEmail(domaine, email);
    }

    @Override
    public List<Account> findAllByDomaine(String domaine) {
        return accountMongoRepository.findAllByDomaine(domaine);
    }

    @Override
    public Account updateAccount(String domaine, String email, String name, String firstName) {
        return null;
    }

    @Override
    public Optional<Account> findById(String id) {
        return accountMongoRepository.findById(id);
    }
}
