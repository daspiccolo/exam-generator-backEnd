package br.com.dasp.examgeneratorfront.custom;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Debora Piccolo.
 */
public class CustomRestTemplate extends RestTemplate {
    private CustomRestTemplate(){
        this.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

    }
}
