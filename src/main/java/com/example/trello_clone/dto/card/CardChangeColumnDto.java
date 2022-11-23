package com.example.trello_clone.dto.card;

import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardChangeColumnDto {
    private Long id;
    private Long boardColumnId;
}
