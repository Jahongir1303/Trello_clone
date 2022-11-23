package com.example.trello_clone.dto.board;


import com.example.trello_clone.dto.base.BaseGenericDto;
import com.example.trello_clone.enums.board.BoardVisibilityType;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BoardCreateDto implements BaseGenericDto {
    private String name;
    @Builder.Default
    private BoardVisibilityType visibilityType = BoardVisibilityType.PUBLIC;
    private Long workspaceId;
}
