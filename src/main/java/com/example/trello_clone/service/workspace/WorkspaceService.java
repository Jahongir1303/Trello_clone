package com.example.trello_clone.service.workspace;

import com.example.trello_clone.dto.workspace.WorkspaceChangeVisibilityDto;
import com.example.trello_clone.dto.workspace.WorkspaceCreateDto;
import com.example.trello_clone.dto.workspace.WorkspaceDto;
import com.example.trello_clone.dto.workspace.WorkspaceMemberDto;

import java.util.List;




public interface WorkspaceService {
    WorkspaceDto save(WorkspaceCreateDto workspaceCreateDto);

    WorkspaceDto get(Long id) throws IllegalAccessException;

    void addMember(WorkspaceMemberDto dto);

    List<WorkspaceDto> getAll();

    void changeVisibility(WorkspaceChangeVisibilityDto dto);

    void removeMember(WorkspaceMemberDto dto);
}
