package com.example.trello_clone.dto.workspace;


import com.example.trello_clone.dto.auth.UserDto;
import com.example.trello_clone.dto.board.BoardDto;
import com.example.trello_clone.enums.workspace.WorkspaceType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkspaceDto {
    private Long id;
    private String name;
    private Long createdBy;
    private WorkspaceType type;
    private String description;
    private Boolean isVisible;
    private Set<BoardDto> boards;
    private Set<UserDto> members;
}
