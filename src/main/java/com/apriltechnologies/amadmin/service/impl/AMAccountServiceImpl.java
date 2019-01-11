package com.apriltechnologies.amadmin.service.impl;

import com.apriltechnologies.amadmin.error.CreateAccountError;
import com.apriltechnologies.amadmin.model.AMCreateAccountResponse;
import com.apriltechnologies.amadmin.model.Account;

import com.apriltechnologies.amadmin.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Qualifier("amService")
public class AMAccountServiceImpl implements AccountService {

    String amBaseUri;
    RestTemplate restTemplate;

    private static String CREATE_SUFFIXE_URL = "/create";
    private static String UPDATE_SUFFIXE_URL = "/update";
    private static String DELETE_SUFFIXE_URL = "/delete";
    private static String EXISTS_SUFFIXE_URL = "/exists";
    private static String GET_SUFFIXE_URL = "/get";

    @Autowired
    public AMAccountServiceImpl(@Value("${am.baseUri}") String amBaseUri, RestTemplate restTemplate) {
        this.amBaseUri = amBaseUri;
        this.restTemplate = restTemplate;
    }

    @Override
    public Account findByEmail(String domaine, String email) {
        return null;
    }

    @Override
    public List<Account> findAllByDomaine(String domaine) {
        return null;
    }

    @Override
    public String createAccount(Account account) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("email", account.getEmail());
        body.add("name", account.getNom());
        body.add("firstName", account.getPrenom());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<AMCreateAccountResponse> responseEntity;
        try {
            String url = amBaseUri + "/" + account.getDomaine() + CREATE_SUFFIXE_URL;
            responseEntity = restTemplate.postForEntity(url, request, AMCreateAccountResponse.class);
        } catch (RestClientException exception) {
            log.error("", exception);
            throw new CreateAccountError();
        }


        return Optional.ofNullable(responseEntity.getBody().getId())
                .orElseThrow(CreateAccountError::new);


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
    public Account updateAccount(String domaine, String email, String name, String firstName) {
        return null;
    }

    @Override
    public Optional<Account> findById(String id) {
        return Optional.empty();
    }
}
