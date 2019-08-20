package com.company.appli.service.impl;

import com.company.appli.model.Account;
import com.company.appli.repository.mongodb.AccountMongoRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

@SpringBootTest
@Slf4j
public class AccountTestIT {

    @Autowired
    AccountMongoRepositoryImpl accountMongoRepository;

    @Autowired
    CacheManager cacheManager;

    @Test
    public void testInsertAccount() {
        Account account = Account.builder()
                .domaine("mondomaine")
                .build();
        Account retour = accountMongoRepository.insertAccount(account);
        log.info(retour.getId());
    }

    @Test
    public void testSelectAccount() {
        //5d5c542dd61c9a28d7d49a51
        Account retour = accountMongoRepository.findByEmail("mondomaine", null);
        Account retour2 = accountMongoRepository.findByEmail("mondomaine", null);
        Account retour3 = accountMongoRepository.findByEmail("mondomaine", null);
        log.info(retour.getId());
    }
}
