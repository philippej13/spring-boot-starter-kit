package com.company.appli.configuration.security;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class AmAdminRepositoryConfiguration extends AbstractMongoConfiguration {

    @Value("${mongo.port}")
    int port;

    @Value("${mongo.host}")
    String host;

    @Value("${mongo.databaseName}")
    String databaseName;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(host, port);
    }

    @Override
    public MongoTemplate mongoTemplate() {return new MongoTemplate(mongoClient(), getDatabaseName()); }

}