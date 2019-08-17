package com.company.appli.configuration;

import lombok.extern.java.Log;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties("spring.data.elasticsearch")
@Log
public class ElasticSearchConfiguration extends AbstractFactoryBean {

    //@Value("${spring.data.elasticsearch.urls}")
    private List<String> urls = new ArrayList<>();
    @Value("${spring.data.elasticsearch.index}")
    private String index;
    @Value("${spring.data.elasticsearch.username}")
    private String username;
    @Value("${spring.data.elasticsearch.password}")
    private String password;
    @Value("${spring.data.elasticsearch.proxyurl:#{null}}")
    private String proxyUrl;
    @Value("${spring.data.elasticsearch.connecttimeout}")
    private int connectTimeout;
    @Value("${spring.data.elasticsearch.sockettimeout}")
    private int socketTimeout;


    private RestHighLevelClient restHighLevelClient;

    @Override
    public void destroy() {
        try {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        } catch (final Exception e) {
            logger.error("Error closing ElasticSearch client: ", e);
        }
    }

    @Override
    public Class<RestHighLevelClient> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public RestHighLevelClient createInstance() {
        return buildClient();
    }

    private RestHighLevelClient buildClient() {
        try {

            //Crédentials
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(this.username, this.password));

            RestClientBuilder.HttpClientConfigCallback httpClientConfigCallback = new RestClientBuilder.HttpClientConfigCallback() {
                @Override
                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                    return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                }
            };

            //Proxy
            RestClientBuilder.RequestConfigCallback requestConfigCallback = null;
            if (proxyUrl != null) {
                requestConfigCallback = new RestClientBuilder.RequestConfigCallback() {
                    @Override
                    public RequestConfig.Builder customizeRequestConfig(
                            RequestConfig.Builder requestConfigBuilder) {
                        return requestConfigBuilder
                                .setConnectTimeout(connectTimeout)
                                .setSocketTimeout(socketTimeout)
                                .setProxy(HttpHost.create(proxyUrl));
                    }
                };
            }

            HttpHost[] listeHttpHosts = new HttpHost[urls.size()];
            int i = 0;
            for (String url : urls) {
                logger.info("ES url found : " + url);
                listeHttpHosts[i] = HttpHost.create(url);
                i++;
            }

            RestClientBuilder restClientBuilder = RestClient.builder(
                    //new HttpHost("52.166.13.207", 9200, "http"))
                    listeHttpHosts);

            restClientBuilder.setHttpClientConfigCallback(httpClientConfigCallback);
            if (requestConfigCallback != null) {
                restClientBuilder.setRequestConfigCallback(requestConfigCallback);
            }

            restHighLevelClient = new RestHighLevelClient(restClientBuilder);



        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return restHighLevelClient;
    }


    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getIndex() {
        return index;
    }
}