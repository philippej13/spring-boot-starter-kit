package com.apriltechnologies.amadmin.service.impl;

import com.apriltechnologies.amadmin.error.CreateAccountError;
import com.apriltechnologies.amadmin.model.AMCreateAccountResponseDTO;
import com.apriltechnologies.amadmin.model.AccountDTO;
import com.apriltechnologies.amadmin.service.AMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.Optional;

@Service
@Slf4j
public class AMServiceImpl implements AMService {

    private String amBaseUri;
    private RestTemplate restTemplate;

    private static String CREATE_SUFFIXE_URL = "/create";
    private static String UPDATE_SUFFIXE_URL = "/update";
    private static String DELETE_SUFFIXE_URL = "/delete";
    private static String EXISTS_SUFFIXE_URL = "/exists";
    private static String GET_SUFFIXE_URL = "/get";

    @Autowired
    public AMServiceImpl(@Value("${am.baseUri}") String amBaseUri, RestTemplate restTemplate) {
        this.amBaseUri = amBaseUri;
        this.restTemplate = restTemplate;
    }

    @Override
    public String createAccount(String domaine, String email, String name, String firstName) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("email", email);
        body.add("name", name);
        body.add("firstName", "firstName");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<AMCreateAccountResponseDTO> responseEntity;

        try {
            String url = amBaseUri + "/" + domaine + CREATE_SUFFIXE_URL;
            responseEntity = restTemplate.postForEntity(url, request, AMCreateAccountResponseDTO.class);
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
    public AccountDTO getAccount(String domaine, String email) {
        return null;
    }

    @Override
    public AccountDTO updateAccount(String domaine, String email, String name, String firstName) {
        return null;
    }
}
