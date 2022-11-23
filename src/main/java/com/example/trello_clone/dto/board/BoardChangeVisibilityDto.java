package com.example.trello_clone.dto.board;


import com.example.trello_clone.enums.board.BoardVisibilityType;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BoardChangeVisibilityDto {
    private Long id;
    private BoardVisibilityType visibility;
}
