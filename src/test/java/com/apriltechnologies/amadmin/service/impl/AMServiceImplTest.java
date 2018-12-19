package com.apriltechnologies.amadmin.service.impl;

import com.apriltechnologies.amadmin.model.AMCreateAccountResponseDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AMServiceImplTest {

    @InjectMocks
    private AMServiceImpl amService;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void createAccount_ok() {
        String id = "5";
        amService.amBaseUri = "http://recapigra-vli01.april.interne.fr:8092/";
        //On mocke la réponse du restTemplate
        AMCreateAccountResponseDTO amCreateAccountResponseDTO =
                new AMCreateAccountResponseDTO("domaine", "email", "created", id);
        ResponseEntity<AMCreateAccountResponseDTO> responseEntity = new ResponseEntity<>(amCreateAccountResponseDTO, HttpStatus.OK);
        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(AMCreateAccountResponseDTO.class))).thenReturn(responseEntity);

        //On appelle le service AMService
        String result = amService.createAccount("domaine", "email", "name", "firstname");

        //On vérifie le test
        assertEquals(result, id);
    }
}
