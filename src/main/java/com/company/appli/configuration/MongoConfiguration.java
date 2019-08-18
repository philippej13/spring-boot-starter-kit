package com.company.appli.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ConditionalOnProperty(name = "repository.mongo.active", havingValue = "true")
public class MongoConfiguration {


    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Bean
    public MongoClient mongo() {
        return new MongoClient(new MongoClientURI(uri));
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "starterkit");
    }
}
