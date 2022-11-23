package com.example.trello_clone.service.card;


import com.example.trello_clone.dto.card.CardAddMemberDto;
import com.example.trello_clone.dto.card.CardChangeColumnDto;
import com.example.trello_clone.dto.card.CardCreateDto;
import com.example.trello_clone.dto.card.CardDto;
import com.example.trello_clone.dto.comment.CommentCreateDto;

public interface CardService {
    CardDto save(CardCreateDto dto);

    CardDto addMember(CardAddMemberDto dto);

    CardDto addComment(CommentCreateDto dto);

    CardDto changeColumn(CardChangeColumnDto dto);

    void deleteCard(Long id);
}
