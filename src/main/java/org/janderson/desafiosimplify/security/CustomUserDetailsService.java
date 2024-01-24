package org.janderson.desafiosimplify.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.janderson.desafiosimplify.Repository.UserRepository;
import org.janderson.desafiosimplify.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;


@Service
public class CustomUserDetailsService extends OncePerRequestFilter {

    private final String BASIC="Basic ";
    private final String AUTHORIZATION="Authorization";
    @Autowired
    private UserRepository repository;


    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(!isBasicAuthentication(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String[] credentials = decodeBase64(getHeader(request).replace(BASIC, ""))
                .split(":");
//        System.out.println(Arrays.toString(credentials));


        User user= repository.findByEmail(credentials[0]);


        if (user==null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("User does not exist");
            return;
        }

        if(checkPasswordAndUsername(credentials,user)) {
            var authToken= new UsernamePasswordAuthenticationToken(credentials[0],null,null);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Username or password invalid!!");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkPasswordAndUsername(String[] credentials, User user) {
        boolean checkPassword= passwordEncoder().matches(credentials[1],user.getPassword());
        boolean checkUserName= credentials[0].equals(user.getEmail());
        return checkPassword && checkUserName;
    }


    private boolean isBasicAuthentication(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);
        return header != null && header.startsWith(BASIC);
    }

    private String decodeBase64(String base64) {
        byte[] decodeBytes = Base64.getDecoder().decode(base64);
        return new String(decodeBytes);
    }

    private String getHeader(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }
}

