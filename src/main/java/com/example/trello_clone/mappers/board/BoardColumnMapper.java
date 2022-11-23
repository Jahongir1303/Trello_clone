package com.example.trello_clone.mappers.board;


import com.example.trello_clone.domains.board.BoardColumn;
import com.example.trello_clone.dto.boardColumn.BoardColumnCreateDto;
import com.example.trello_clone.dto.boardColumn.BoardColumnDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring",
        uses = {CardMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BoardColumnMapper {
    BoardColumn fromCreateDto(BoardColumnCreateDto dto);

    @Mapping(target = "boardId", source = "board.id")
    BoardColumnDto fromBoardColumn(BoardColumn boardColumn);
}
