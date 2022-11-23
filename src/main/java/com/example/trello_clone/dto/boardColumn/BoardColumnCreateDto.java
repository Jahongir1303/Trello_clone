package com.example.trello_clone.dto.boardColumn;


import com.example.trello_clone.dto.base.BaseGenericDto;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BoardColumnCreateDto implements BaseGenericDto {
    private String name;
    private Integer order;
    private Long boardId;
}
