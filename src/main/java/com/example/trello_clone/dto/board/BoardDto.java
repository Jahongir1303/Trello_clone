package com.example.trello_clone.dto.board;


import com.example.trello_clone.dto.boardColumn.BoardColumnDto;
import com.example.trello_clone.enums.board.BoardVisibilityType;
import lombok.*;

import java.util.Set;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
    private Long id;
    private String name;
    private BoardVisibilityType visibilityType;
    private Long workspaceId;
    private Set<BoardColumnDto> boardColumns;
}
