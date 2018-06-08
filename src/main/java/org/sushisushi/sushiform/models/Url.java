package org.sushisushi.sushiform.models;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix= "url")
public class Url {
    private static String auth;
    private static String base;

    public static String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public static String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }







}