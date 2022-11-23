package com.example.trello_clone.mappers.board;

import com.example.trello_clone.domains.board.Card;
import com.example.trello_clone.dto.card.CardCreateDto;
import com.example.trello_clone.dto.card.CardDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {CommentMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CardMapper {
    @Mapping(target = "boardColumnId", source = "boardColumn.id")
    CardDto fromCard(Card card);


    Card fromCreateDto(CardCreateDto dto);
}
