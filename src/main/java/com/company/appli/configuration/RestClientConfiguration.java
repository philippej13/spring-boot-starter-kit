package com.company.appli.configuration;

import com.company.appli.handler.RestTemplateResponseErrorHandler;
import com.company.appli.interceptor.ServiceRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestClientConfiguration {

    @Autowired
    ServiceRequestInterceptor serviceRequestInterceptor;

    /**
     * Un RestTemplate avec intercepteur et handler
     * @return
     */
    @Bean
    @Qualifier("restTemplateAM")
    public RestTemplate restTemplateAM() {
       RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors
                = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(serviceRequestInterceptor);
        restTemplate.setInterceptors(interceptors);
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate;
    }

    /**
     * Un RestTemplate classique
     * @return
     */
    @Bean
    @Qualifier("restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
