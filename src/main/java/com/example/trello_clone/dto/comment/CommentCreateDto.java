package com.example.trello_clone.dto.comment;

import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentCreateDto {
    private String creatorEmail;
    private String text;
    private String attachment;
    private Long cardId;
}
