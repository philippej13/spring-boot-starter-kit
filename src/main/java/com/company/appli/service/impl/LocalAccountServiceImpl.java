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

   /* @Autowired
    @Qualifier("mongoRepository")
    AccountRepositoryCustom accountMongoRepository;

    @Autowired
    @Qualifier("elasticsearchRepository")
    AccountRepositoryCustom accountElasticsearchRepository;
*/
    @Autowired
    @Qualifier("accountRepositoryFactory")
    AccountRepositoryCustom accountRepository;

    @Override
    public String createAccount(Account account) {
        //Création dans Mongo
        log.info("Insertion dans en base");
        Account result = accountRepository.insertAccount(account);

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
        return accountRepository.findByEmail(domaine, email);
    }

    @Override
    public List<Account> findAllByDomaine(String domaine) {
        return accountRepository.findAllByDomaine(domaine);
    }

    @Override
    public Account updateAccount(String domaine, String email, String name, String firstName) {
        return null;
    }

    @Override
    public Optional<Account> findById(String id) {
        return accountRepository.findById(id);
    }
}
