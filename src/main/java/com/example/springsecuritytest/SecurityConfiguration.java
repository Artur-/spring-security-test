package com.example.springsecuritytest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.util.ServletRequestPathUtils;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final class FailingMatcher implements RequestMatcher {
        @Override
        public boolean matches(HttpServletRequest request) {
            System.out.println("Request to " + ServletRequestPathUtils.parseAndCache(request));
            return true;
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.requestMatcher(new FailingMatcher());
    }

}
