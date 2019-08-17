package com.company.appli.repository.elasticsearch;

import com.company.appli.configuration.ElasticSearchConfiguration;
import com.company.appli.error.CreateAccountError;
import com.company.appli.model.Account;
import com.company.appli.repository.AccountRepositoryCustom;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
@Qualifier("elasticsearchRepository")
public class AccountElasticsearchRepositoryImpl implements AccountRepositoryCustom {

    @Autowired
    ElasticSearchConfiguration elasticSearchConfiguration;

    private RestHighLevelClient restHighLevelClient;
    private ObjectMapper objectMapper;


    public AccountElasticsearchRepositoryImpl(ObjectMapper objectMapper ,RestHighLevelClient restHighLevelClient) {
        this.objectMapper = objectMapper;
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public Account findByEmail(String domaine, String email) {
        return null;
    }

    @Override
    public Optional<Account> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Account> findAllByDomaine(String domaine) {
        return null;
    }

    @Override
    public Account insertAccount(Account account) {
        Map dataMap = objectMapper.convertValue(account, Map.class);
        IndexRequest indexRequest = new IndexRequest(elasticSearchConfiguration.getIndex() )
                .source(dataMap);
        try {
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (java.io.IOException ex){
            String errorMsg = "Error inserting Account in ES ";
            log.error(errorMsg, ex);
            throw new CreateAccountError(ex);
        }
        return account;
    }
}
