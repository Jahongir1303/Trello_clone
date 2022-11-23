package com.example.trello_clone.service.token;


import com.example.trello_clone.configs.security.UserDetails;

public interface TokenService {

    String generateToken(UserDetails userDetails);

    Boolean isValid(String token);


}
