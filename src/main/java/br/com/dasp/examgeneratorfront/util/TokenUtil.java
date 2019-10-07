package br.com.dasp.examgeneratorfront.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Debora Piccolo.
 */
public class TokenUtil {
    public String getTokenFromCookies(HttpServletRequest request) {
        if (request.getCookies() == null) return "";
        List<Cookie> cookieList = asList(request.getCookies());
        return getCookieByKey(cookieList, "token");
    }

    public boolean isExpirationTimeFromCookieValid(HttpServletRequest request) {
        if (request.getCookies() == null) return false;
        List<Cookie> cookieList = asList(request.getCookies());
        String expirationTime = getCookieByKey(cookieList, "expirationTime");
        return validateIfTimeNowIsBeforeTokenExpires(expirationTime);
    }

    private String getCookieByKey(List<Cookie> cookieList, String key) {
        return cookieList.stream()
                .filter(cookie -> cookie.getName().equals(key))
                .map(Cookie::getValue)
                .findFirst()
                .orElse("");
    }

    private boolean validateIfTimeNowIsBeforeTokenExpires(String expirationTime) {
        if (expirationTime.isEmpty()) return false;
         LocalDateTime tokenExpirationTime = LocalDateTime.parse(expirationTime);
        return LocalDateTime.now().isBefore(tokenExpirationTime);
    }
}