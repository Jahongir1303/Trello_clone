package com.example.trello_clone.dto.jwt;


public record JwtResponseDto(
        String accessToken,
        String refreshToken,
        String tokenType) {
}
