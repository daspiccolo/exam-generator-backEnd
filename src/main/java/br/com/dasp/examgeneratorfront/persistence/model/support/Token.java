package br.com.dasp.examgeneratorfront.persistence.model.support;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Debora Piccolo.
 */
public class Token implements Serializable {
    private String token;

    @JsonProperty("exp")
    private LocalDateTime expirationTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
