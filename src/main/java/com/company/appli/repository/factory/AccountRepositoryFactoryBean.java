package com.company.appli.repository.factory;

import com.company.appli.repository.AccountRepositoryCustom;
import com.company.appli.repository.elasticsearch.AccountElasticsearchRepository;
import com.company.appli.repository.elasticsearch.AccountElasticsearchRepositoryImpl;
import com.company.appli.repository.mongodb.AccountMongoRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@Qualifier("accountRepositoryFactory")
public class AccountRepositoryFactoryBean implements FactoryBean<AccountRepositoryCustom> {

    @Value("${repository.localAccount.repository.impl}")
    private String implementationClass;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public AccountRepositoryCustom getObject() throws Exception {
            if (implementationClass.equals("com.company.appli.repository.elasticsearch.AccountElasticsearchRepositoryImpl")) {
                log.info("Implémentation [" + implementationClass + "] trouvé");
                return applicationContext.getBean(AccountElasticsearchRepositoryImpl.class);
            } else  if (implementationClass.equals("com.company.appli.repository.mongodb.AccountMongoRepositoryImpl")) {
                log.info("Implémentation [" + implementationClass + "] trouvé");
                return applicationContext.getBean(AccountMongoRepositoryImpl.class);
            } else {
                throw new Exception("Implémentation [" + implementationClass + "] introuvable");
            }
    }

    @Override
    public Class<?> getObjectType() {
        return AccountRepositoryCustom.class;
    }
}
