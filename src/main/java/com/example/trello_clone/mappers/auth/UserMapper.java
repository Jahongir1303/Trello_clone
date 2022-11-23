package com.example.trello_clone.mappers.auth;


import com.example.trello_clone.domains.auth.AuthUser;
import com.example.trello_clone.dto.auth.UserDto;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto fromUser(AuthUser authUser);
}
