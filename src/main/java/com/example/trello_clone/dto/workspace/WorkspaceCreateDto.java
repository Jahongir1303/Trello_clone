package com.example.trello_clone.dto.workspace;

import com.example.trello_clone.dto.base.BaseGenericDto;
import com.example.trello_clone.enums.workspace.WorkspaceType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkspaceCreateDto implements BaseGenericDto {
    private String name;
    private Long userId;
    private WorkspaceType type;
    private String description;
}
