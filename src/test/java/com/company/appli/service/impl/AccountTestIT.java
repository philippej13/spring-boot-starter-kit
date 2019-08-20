package com.company.appli.service.impl;

import com.company.appli.model.Account;
import com.company.appli.repository.AccountRepositoryCustom;
import com.company.appli.repository.mongodb.AccountMongoRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AccountTestIT {

    @Autowired
    AccountMongoRepositoryImpl accountMongoRepository;

    @Test
    public void testInsertAccount() {
        Account account = Account.builder()
                .domaine("mondomaine")
                .build();
        Account retour = accountMongoRepository.insertAccount(account);
        log.info(retour.getId());
    }
}
