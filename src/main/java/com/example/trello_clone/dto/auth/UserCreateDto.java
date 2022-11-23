package com.example.trello_clone.dto.auth;


import com.example.trello_clone.dto.base.BaseGenericDto;





public record UserCreateDto(String email, String password) implements BaseGenericDto {
}
