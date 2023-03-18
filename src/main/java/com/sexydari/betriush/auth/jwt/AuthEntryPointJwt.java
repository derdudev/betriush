package com.sexydari.betriush.auth.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO: UPDATE TO https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication/blob/master/src/main/java/com/bezkoder/springjwt/security/jwt/AuthEntryPointJwt.java

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    // This method will be triggered anytime unauthenticated User requests a secured HTTP resource and an AuthenticationException is thrown.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized"); // HttpServletResponse.SC_UNAUTHORIZED -> 401 Status Code
    }
}
