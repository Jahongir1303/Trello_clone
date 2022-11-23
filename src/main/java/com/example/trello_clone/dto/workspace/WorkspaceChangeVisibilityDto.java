package com.example.trello_clone.dto.workspace;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkspaceChangeVisibilityDto {
    private Long id;
    private Boolean visibility;
}
