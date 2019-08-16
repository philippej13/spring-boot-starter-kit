package com.company.appli.service.impl;


import com.company.appli.configuration.AMServiceConfiguration;
import com.company.appli.error.CreateAccountError;
import com.company.appli.model.AMTokenResponse;
import com.company.appli.service.AMTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
@Slf4j
public class AMTokenServiceImpl implements AMTokenService {


    RestTemplate restTemplate;
    AMServiceConfiguration amServiceConfiguration;

    private static String GET_TOKEN_URL = "admin/token";

    @Autowired
    public AMTokenServiceImpl(@Qualifier("restTemplate") RestTemplate restTemplate,
                              AMServiceConfiguration amServiceConfiguration) {

        this.restTemplate = restTemplate;
        this.amServiceConfiguration = amServiceConfiguration;
    }

    @Override
    public String getAdminAccessToken() {
      HttpEntity<String> request = new HttpEntity<>(createHeaders(amServiceConfiguration.getAmManagementAdminLogin(),
              amServiceConfiguration.getAmManagementAdminPassword()));

        ResponseEntity<AMTokenResponse> responseEntity;
        try {
            String url = amServiceConfiguration.getAmManagementBaseUri() + GET_TOKEN_URL;
            log.debug("Url Get Token : " + url);
            responseEntity = restTemplate.postForEntity(url, request, AMTokenResponse.class);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new CreateAccountError(exception.getMessage());
        }


        return Optional.ofNullable(responseEntity.getBody().getAccess_token())
                .orElseThrow();

    }

    HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }


}
