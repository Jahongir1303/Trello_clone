package com.example.trello_clone.mappers.board;


import com.example.trello_clone.domains.board.Board;
import com.example.trello_clone.dto.board.BoardCreateDto;
import com.example.trello_clone.dto.board.BoardDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring", uses = {CommentMapper.class, BoardColumnMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BoardMapper {
    Board fromCreateDto(BoardCreateDto dto);

    @Mapping(target = "workspaceId", source = "workspace.id")
    BoardDto fromBoard(Board board);
}
