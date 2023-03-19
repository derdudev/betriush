package com.sexydari.betriush.auth.jwt;

import com.sexydari.betriush.auth.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = parseJwt(request); // get JWT from the Authorization header by removing Bearer prefix
            if(jwt != null && jwtUtils.validateJwtToken(jwt)){ // if JWT is valid
                String username = jwtUtils.getUserNameFromJwtToken(jwt); // get username from JWT

                UserDetails userDetails = userDetailsService.loadUserByUsername(username); // get userDetails from username to create an Authentication Object
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication); // set current UserDetails in SecurityContext
                // everytime you want the UserDetails: UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            }
        } catch(Exception e){
            logger.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request){
        List<Cookie> potCookies = Arrays.stream(request.getCookies()).collect(Collectors.groupingBy(i -> i.getName())).get("btrusr");
        String cookieAuth = "";
        if(potCookies != null && potCookies.size() != 0){
            cookieAuth = potCookies.get(0).getValue();
        }

        if(StringUtils.hasLength(cookieAuth)){
            return cookieAuth;
        }
        return null;
    }
}
