package com.example.trello_clone.validators.workspace;


import com.example.trello_clone.configs.security.UserDetails;
import com.example.trello_clone.domains.auth.AuthUser;
import com.example.trello_clone.domains.workspace.Workspace;
import com.example.trello_clone.dto.workspace.WorkspaceChangeVisibilityDto;
import com.example.trello_clone.dto.workspace.WorkspaceCreateDto;
import com.example.trello_clone.dto.workspace.WorkspaceMemberDto;
import com.example.trello_clone.dto.workspace.WorkspaceUpdateDto;
import com.example.trello_clone.enums.auth.UserStatus;
import com.example.trello_clone.enums.workspace.WorkspaceType;
import com.example.trello_clone.exceptions.GenericNotFoundException;
import com.example.trello_clone.exceptions.ValidationException;
import com.example.trello_clone.repository.auth.UserRepository;
import com.example.trello_clone.repository.workspace.WorkspaceRepository;
import com.example.trello_clone.validators.base.AbstractValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class WorkspaceValidator extends AbstractValidator<WorkspaceCreateDto, WorkspaceUpdateDto, Long> {
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;

    @Override
    public void validateKey(Long id) throws ValidationException {
        workspaceRepository.findById(id).
                orElseThrow(() -> new ValidationException("Workspace not found by id : 5s".formatted(id)));
    }

    @Override
    public void validOnCreate(WorkspaceCreateDto dto) throws ValidationException {
        String name = dto.getName();
        WorkspaceType type = dto.getType();
        Long userId = dto.getUserId();
        if (Objects.isNull(name) || name.isBlank())
            throw new ValidationException("workspace name cannot be empty");
        if (Objects.isNull(type))
            throw new ValidationException("workspace type cannot be null");
        userRepository.findById(userId)
                .orElseThrow(() -> new ValidationException("User not found by id: %s".formatted(userId)));
    }

    @Override
    public void validOnUpdate(WorkspaceUpdateDto dto) throws ValidationException {

    }

    public void validOnAddMember(WorkspaceMemberDto dto) throws ValidationException {
        Workspace workspace = workspaceRepository.findById(dto.getId()).
                orElseThrow(() -> new ValidationException("Workspace not found by id : %s".formatted(dto.getId())));
        String userEmail = dto.getMemberEmail();
        AuthUser authUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new GenericNotFoundException(
                        "user not found by email: %s".formatted(userEmail)));
        if (!authUser.getIsActive() || authUser.getStatus().equals(UserStatus.IN_ACTIVE))
            throw new GenericNotFoundException("user not found by email: %s".formatted(userEmail));
        if (workspace.getCreatedBy().equals(authUser) || workspace.getMembers().contains(authUser))
            throw new IllegalArgumentException("already member");
    }

    public void validateOnChangeVisibility(WorkspaceChangeVisibilityDto dto) {
        Workspace workspace = workspaceRepository.findById(dto.getId()).
                orElseThrow(() -> new ValidationException("Workspace not found by id : %s".formatted(dto.getId())));
        validateWorkspaceCreator(workspace);
    }

    public void validateWorkspaceCreator(Workspace workspace) {
        boolean isUserNotCreatorOfWorkspace = !workspace.getCreatedBy().getId().equals(getUserDetails().getId());
        if (isUserNotCreatorOfWorkspace)
            throw new ValidationException("You do not have permission to change visibility");
    }

    private UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

    public void validateOnRemoveMember(WorkspaceMemberDto dto) {
        Workspace workspace = workspaceRepository.findById(dto.getId()).
                orElseThrow(() -> new ValidationException("Workspace not found by id : %s".formatted(dto.getId())));
        validateWorkspaceCreator(workspace);
        AuthUser authUser = userRepository.findByEmail(dto.getMemberEmail())
                .orElseThrow(() -> new GenericNotFoundException("User not found by email: " + dto.getMemberEmail()));
        if (!workspace.getMembers().contains(authUser))
            throw new ValidationException("User is not member of workspace with id: %s, user: %s ".formatted(dto.getId(), dto.getMemberEmail()));
    }
}
