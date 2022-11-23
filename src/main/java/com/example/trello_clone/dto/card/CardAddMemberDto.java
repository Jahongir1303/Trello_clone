package com.example.trello_clone.dto.card;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardAddMemberDto {
    private Long id;
    private String memberEmail;
}
