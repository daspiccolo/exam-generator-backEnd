package br.com.dasp.examgeneratorfront.persistence.dao;

import br.com.dasp.examgeneratorfront.custom.CustomRestTemplate;
import br.com.dasp.examgeneratorfront.persistence.model.support.ErrorDetail;
import br.com.dasp.examgeneratorfront.persistence.model.support.Token;
import br.com.dasp.examgeneratorfront.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;

import static org.springframework.http.HttpMethod.POST;

public class LoginDAO implements Serializable { // classe responsavel por fazer a requisicao para o |TOKEn
    private final String BASE_URL = "http://localhost:8085/login";

    private final CustomRestTemplate restTemplate;

    private final JsonUtil jsonUtil;

    @Inject
    public LoginDAO(CustomRestTemplate restTemplate, JsonUtil jsonUtil) {
        this.restTemplate = restTemplate;
        this.jsonUtil = jsonUtil;
    }

    @ExceptionHandler
    public Token loginReturnToken(String username, String password) throws IOException {
        String loginJson = "{\"username\":" + addQuotes(username) + ",\"password\": " + addQuotes(password) + "}";
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        ResponseEntity<Token> tokenExchange = restTemplate.exchange(BASE_URL, POST, new HttpEntity<>(loginJson, jsonUtil.createJsonHeaders()), Token.class);
        return tokenExchange.getBody();
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    private String addQuotes(String value) {
        return new StringBuilder(300).append("\"").append(value).append("\"").toString();
    }

    private HttpHeaders createJsonHeader() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return header;
    }


}
