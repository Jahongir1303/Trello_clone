package com.example.trello_clone.validators.board;


import com.example.trello_clone.configs.security.UserDetails;
import com.example.trello_clone.domains.board.Board;
import com.example.trello_clone.domains.workspace.Workspace;
import com.example.trello_clone.dto.board.BoardChangeVisibilityDto;
import com.example.trello_clone.dto.board.BoardCreateDto;
import com.example.trello_clone.dto.board.BoardUpdateDto;
import com.example.trello_clone.enums.board.BoardVisibilityType;
import com.example.trello_clone.exceptions.GenericNotFoundException;
import com.example.trello_clone.exceptions.ValidationException;
import com.example.trello_clone.repository.board.BoardRepository;
import com.example.trello_clone.repository.workspace.WorkspaceRepository;
import com.example.trello_clone.validators.base.AbstractValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class BoardValidator extends AbstractValidator<BoardCreateDto, BoardUpdateDto, Long> {
    private final WorkspaceRepository workspaceRepository;
    private final BoardRepository boardRepository;

    @Override
    public void validateKey(Long id) throws ValidationException {
        Board board = getBoard(id);
        validateAccessiblity(board);
    }

    private Board getBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException("Board not found by id : %s".formatted(id)));
        if (board.getIsDeleted())
            throw new ValidationException("Board not available");
        return board;
    }

    public void validateAccessiblity(Board board) {
        BoardVisibilityType visibilityType = board.getVisibilityType();
        if (visibilityType.equals(BoardVisibilityType.PRIVATE)) {
            validateBoardWorkspace(board.getWorkspace());
        } else if (visibilityType.equals(BoardVisibilityType.WORKSPACE_MEMBERS)) {
            checkMembership(board);
        }
    }

    public void checkMembership(Board board) {
        boolean isNotMember = board.getWorkspace()
                .getMembers()
                .stream()
                .noneMatch(user -> user.getId().equals(getUserDetails().getId()));
        if (isNotMember)
            throw new ValidationException("you do not have permission on this workspace");
    }

    @Override
    public void validOnCreate(BoardCreateDto dto) throws ValidationException {
        String name = dto.getName();
        if (Objects.isNull(name) || name.isBlank()) {
            throw new ValidationException("board name cannot be empty");
        }
        Workspace workspace = workspaceRepository.findById(dto.getWorkspaceId())
                .orElseThrow(() -> new GenericNotFoundException("Workspace not found by id : %s".formatted(dto.getWorkspaceId())));
        validateBoardWorkspace(workspace);

    }

    private void validateBoardWorkspace(Workspace workspace) {
        boolean workspaceNotBelongsToCurrentUser = !workspace.getCreatedBy().getId().equals(getUserDetails().getId());
        if (workspace.getIsDeleted() || workspaceNotBelongsToCurrentUser) {
            throw new ValidationException("you do not have permission on this workspace");
        }
    }

    @Override
    public void validOnUpdate(BoardUpdateDto dto) throws ValidationException {

    }

    private UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

    public void validateOnChangeVisibility(BoardChangeVisibilityDto dto) {
        Long id = dto.getId();
        Board board = getBoard(id);
        if (Objects.isNull(dto.getVisibility()))
            throw new ValidationException("Visibility type can not be null");
        validateBoardWorkspace(board.getWorkspace());
    }

    public void validateOnDelete(Long id) {
        Board board = getBoard(id);
        validateBoardWorkspace(board.getWorkspace());
    }
}

