package com.example.trello_clone.controller;

import com.example.trello_clone.dto.workspace.WorkspaceChangeVisibilityDto;
import com.example.trello_clone.dto.workspace.WorkspaceCreateDto;
import com.example.trello_clone.dto.workspace.WorkspaceDto;
import com.example.trello_clone.dto.workspace.WorkspaceMemberDto;
import com.example.trello_clone.service.workspace.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workspace")
@PreAuthorize("isAuthenticated()")
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WorkspaceDto> createWorkspace(@Valid @RequestBody WorkspaceCreateDto workspaceCreateDto) {
        WorkspaceDto workspaceDto = workspaceService.save(workspaceCreateDto);
        return new ResponseEntity<>(workspaceDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkspaceDto>> getAll() {
        List<WorkspaceDto> workspaceDtos = workspaceService.getAll();
        return new ResponseEntity<>(workspaceDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WorkspaceDto> getWorkspace(@PathVariable Long id) throws IllegalAccessException {
        WorkspaceDto workspaceDto = workspaceService.get(id);
        return new ResponseEntity<>(workspaceDto, HttpStatus.OK);
    }

    @PatchMapping(value = "/addMember")
    public ResponseEntity<Void> addMember(@Valid @RequestBody WorkspaceMemberDto dto) {
        workspaceService.addMember(dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/removeMember")
    public ResponseEntity removeMember(@Valid @RequestBody WorkspaceMemberDto dto) {
        workspaceService.removeMember(dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/changeVisibility")
    public ResponseEntity changeVisibility(@Valid @RequestBody WorkspaceChangeVisibilityDto dto) {
        workspaceService.changeVisibility(dto);
        return ResponseEntity.noContent().build();
    }
}
