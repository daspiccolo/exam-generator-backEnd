package br.com.dasp.examgeneratorfront.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.faces.annotation.RequestCookieMap;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.util.Map;

import static br.com.dasp.examgeneratorfront.custom.CustomURLEncoderDecoder.decodeUTF8;


/**
 * @author Debora Piccolo.
 */
public class JsonUtil implements Serializable {
    @Inject
    @RequestCookieMap
    private Map<String,Object>cookieMap;

    public  HttpHeaders createJsonHeaders() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return header;
    }
    public  HttpHeaders createTokenizedHeaders() {
        HttpHeaders header = createJsonHeaders();
        Cookie tokenCookie = (Cookie) cookieMap.get("token");
        header.add("Authorization", decodeUTF8(tokenCookie.getValue()));
        return header;
    }


}
