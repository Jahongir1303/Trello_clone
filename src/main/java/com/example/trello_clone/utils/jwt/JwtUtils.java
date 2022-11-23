package com.example.trello_clone.utils.jwt;


import com.example.trello_clone.service.token.AccessTokenService;
import com.example.trello_clone.service.token.RefreshTokenService;

// TODO: 8/20/22 we wanted it to be factory for token services
public class JwtUtils {
    public static final AccessTokenService accessTokenService = new AccessTokenService();
    public static final RefreshTokenService refreshTokenService = new RefreshTokenService();
}
