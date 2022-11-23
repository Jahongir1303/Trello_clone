package com.example.trello_clone.dto.workspace;


import com.example.trello_clone.dto.base.GenericDto;
import com.example.trello_clone.enums.workspace.WorkspaceType;
import lombok.*;




@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkspaceUpdateDto extends GenericDto {
    private Long id;
    private String name;
    private WorkspaceType type;
    private String description;
}
