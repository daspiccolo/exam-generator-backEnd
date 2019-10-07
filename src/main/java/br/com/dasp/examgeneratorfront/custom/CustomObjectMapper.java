package br.com.dasp.examgeneratorfront.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author Debora Piccolo.
 */
public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper(){
        this.registerModule(new JavaTimeModule());
    }
}
