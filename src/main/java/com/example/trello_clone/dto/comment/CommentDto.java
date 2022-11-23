package com.example.trello_clone.dto.comment;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private String creator;
    private String text;
    private String attachment;
    private Long cardId;
}
