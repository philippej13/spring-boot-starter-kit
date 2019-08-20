package com.company.appli.service.impl;

import com.company.appli.model.AMCreateAccountResponse;
import com.company.appli.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AMServiceImplTest {

    @InjectMocks
    private AMAccountServiceImpl amService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    //TODO : Test à réécrire
    @Test
    @Disabled
    public void createAccount_ok() {
        String id = "5";
        //amService.amBaseUri = "http://gravitee-am:8092/";
        //On mocke la réponse du restTemplate
        AMCreateAccountResponse amCreateAccountResponse =
                new AMCreateAccountResponse("domaine", "email", "created", id);
        ResponseEntity<AMCreateAccountResponse> responseEntity = new ResponseEntity<>(amCreateAccountResponse, HttpStatus.OK);
        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(AMCreateAccountResponse.class))).thenReturn(responseEntity);

        //On appelle le service AMService
        Account account = new Account(null, "domaine", "email", "name", "firstname");
        //String result = amService.createAccount(account);
        String result = null;
        //On vérifie le test
        assertEquals(result, id);
    }
}
