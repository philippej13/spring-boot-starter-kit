package com.company.appli.service.impl;


import com.company.appli.configuration.AMServiceConfiguration;
import com.company.appli.error.CreateAccountError;
import com.company.appli.service.AccountService;
import com.company.appli.service.LocalAccountService;
import io.gravitee.am.model.User;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


@Service
@Slf4j
@Qualifier("amService")
public class AMAccountServiceImpl implements AccountService {

    AMServiceConfiguration amServiceConfiguration;
    RestTemplate restTemplate;

    @Autowired
    public AMAccountServiceImpl(@Qualifier("restTemplateAM") RestTemplate restTemplate,
                                AMServiceConfiguration amServiceConfiguration) {
        this.restTemplate = restTemplate;
        this.amServiceConfiguration = amServiceConfiguration;

    }

    @Override
    public Boolean findByUserName(String domaine, String userName) {
        class ListeUsers {
            List<io.gravitee.am.model.User> data;

            public ListeUsers(List<User> data) {
                this.data = data;
            }

            public List<User> getData() {
                return data;
            }

            public void setData(List<User> data) {
                this.data = data;
            }
        }

        String lUri = amServiceConfiguration.getAmManagementBaseUri() + "management/domains/" + domaine + "/users";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(lUri)
                // Add query parameter
                .queryParam("q", userName);

        String response = restTemplate.getForEntity(builder.toUriString(), String.class).getBody();

        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> listNode = null;
        try {
            JsonNode rootNode = mapper.readTree(response);
            listNode = rootNode.findValues("data");

        } catch (IOException e) {
            throw new CreateAccountError(e.getMessage());
        }
        for (Iterator i = listNode.get(0).iterator(); i.hasNext(); ) {
            JsonNode user = (JsonNode) i.next();
            log.info(user.asText());
            if (user.path("username").asText().equals(userName)) {
                return true;
            }
        }


        return false;
    }


    @Override
    public String createAccount(String domaine, String username, String idpId, String email, String firstName, String lastName, HashMap<String, Object> additionalInformation) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        io.gravitee.am.service.model.NewUser amCreateAccountRequest = new io.gravitee.am.service.model.NewUser();
        amCreateAccountRequest.setUsername(username);
        amCreateAccountRequest.setEmail(email);
        amCreateAccountRequest.setFirstName(firstName);
        amCreateAccountRequest.setLastName(lastName);
        amCreateAccountRequest.setDomain(domaine);
        amCreateAccountRequest.setSource(idpId);
        amCreateAccountRequest.setPreRegistration(true);
        amCreateAccountRequest.setAdditionalInformation(additionalInformation);

        HttpEntity<io.gravitee.am.service.model.NewUser> request = new HttpEntity<>(amCreateAccountRequest, headers);

        ResponseEntity<String> responseEntity;
        try {
            String url = amServiceConfiguration.getAmManagementBaseUri() + "management/domains/" + domaine + "/users";
            responseEntity = restTemplate.postForEntity(url, request, String.class);
        } catch (RestClientException exception) {

            log.error("Erreur lors de l'appel à AM", exception);
            throw new CreateAccountError(exception.getMessage());
        }

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode rootNode = mapper.readTree(responseEntity.getBody());
                JsonNode idNode = rootNode.path("id");
                return idNode.asText();
            } catch (IOException e) {
                throw new CreateAccountError(e.getMessage());
            }
        } else {
            throw new CreateAccountError(responseEntity.getStatusCode().value(), responseEntity.getBody());
        }

    }

    @Override
    public boolean deleteAccount(String domaine, String email) {
        return false;
    }


    @Override
    public Boolean findByEmail(String domaine, String email) {
        return null;
    }
}
