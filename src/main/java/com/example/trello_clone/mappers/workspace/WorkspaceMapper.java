package com.example.trello_clone.mappers.workspace;


import com.example.trello_clone.domains.workspace.Workspace;
import com.example.trello_clone.dto.workspace.WorkspaceCreateDto;
import com.example.trello_clone.dto.workspace.WorkspaceDto;
import com.example.trello_clone.dto.workspace.WorkspaceMemberDto;
import com.example.trello_clone.dto.workspace.WorkspaceUpdateDto;
import com.example.trello_clone.mappers.auth.UserMapper;
import com.example.trello_clone.mappers.board.BoardMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring", uses = {BoardMapper.class, UserMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface WorkspaceMapper {
    Workspace fromWorkspaceCreateDto(WorkspaceCreateDto workspaceCreateDto);


    @Mapping(target = "createdBy", source = "createdBy.id")
    WorkspaceDto fromWorkspace(Workspace workspace);

    WorkspaceUpdateDto fromAddMemberToUpdate(WorkspaceMemberDto dto);
}
