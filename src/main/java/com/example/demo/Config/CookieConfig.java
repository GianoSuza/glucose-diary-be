package com.example.demo.Config;

import org.springframework.boot.web.server.Cookie;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookieConfig {

    @Bean
    public Session.Cookie.SameSite cookieSameSite() {
        return Cookie.SameSite.NONE;
    }
}
