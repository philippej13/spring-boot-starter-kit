package com.company.appli.interceptor;


import com.company.appli.service.AMTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * L'intercepteur permet d'intercepter la réponse HTTP avant le renvoie de celle-ci au client (différent du ResponseErrorHandler
 */
@Component
@Slf4j
public class ServiceRequestInterceptor implements ClientHttpRequestInterceptor {

    @Autowired
    AMTokenService amTokenService;

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, body);
        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            log.info("Unauthorized Access . Trying to get a new token ... ");
            try {
                //TODO : ex : Appel de service d'authentification pour récupérer un token
                String accesToken = amTokenService.getAdminAccessToken();
                log.info("Acces Token : " + accesToken);
                httpRequest.getHeaders().set(HttpHeaders.AUTHORIZATION, "Bearer " + accesToken);
            } catch (Exception e) {
                log.error("Fail to get an acces token from Access Mangement");
            }
            response = clientHttpRequestExecution.execute(httpRequest, body);
        }
        return response;
    }
}
