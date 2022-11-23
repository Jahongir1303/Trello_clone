package com.example.trello_clone.dto.boardColumn;

import com.example.trello_clone.dto.base.GenericDto;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BoardColumnUpdateDto extends GenericDto {
    private Long id;
    private String name;
    private Integer order;
    private Long boardId;
}
