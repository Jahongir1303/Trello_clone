package com.example.trello_clone.dto.card;



import com.example.trello_clone.dto.auth.UserDto;
import com.example.trello_clone.dto.comment.CommentDto;
import lombok.*;

import java.util.List;
import java.util.Set;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {
    private Long id;
    private String name;
    private Set<UserDto> members;
    //    private final List<Comment> comments = new ArrayList<>();
    private Long boardColumnId;
    private List<CommentDto> comments;
}
