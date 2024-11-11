package com.onlinebookstore.book_store.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthTokenFilter {

    @Autowired
    private JwtUtils jwtUtils;


}
