package com.example.trello_clone.mappers.board;


import com.example.trello_clone.domains.Comment;
import com.example.trello_clone.dto.comment.CommentCreateDto;
import com.example.trello_clone.dto.comment.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment fromCreateDto(CommentCreateDto dto);

    @Mapping(target = "creator", source = "creator.email")
    @Mapping(target = "cardId", source = "card.id")
    CommentDto fromComment(Comment comment);
}
