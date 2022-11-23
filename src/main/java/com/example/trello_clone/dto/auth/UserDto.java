package com.example.trello_clone.dto.auth;



import com.example.trello_clone.domains.auth.AuthRole;
import com.example.trello_clone.dto.base.GenericDto;
import com.example.trello_clone.enums.auth.UserStatus;
import lombok.*;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto extends GenericDto {
    private String email;
    private Set<AuthRole> roles;
    private UserStatus status;
    private Boolean isActive;

    @Builder(builderMethodName = "childBuilder")
    public UserDto(Long id, String email, Set<AuthRole> roles, UserStatus status, Boolean isActive) {
        super(id);
        this.email = email;
        this.roles = roles;
        this.status = status;
        this.isActive = isActive;
    }
}
