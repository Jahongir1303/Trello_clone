package com.example.trello_clone.validators.board;


import com.example.trello_clone.domains.auth.AuthUser;
import com.example.trello_clone.domains.board.Board;
import com.example.trello_clone.domains.board.BoardColumn;
import com.example.trello_clone.domains.board.Card;
import com.example.trello_clone.domains.workspace.Workspace;
import com.example.trello_clone.dto.card.CardAddMemberDto;
import com.example.trello_clone.dto.card.CardChangeColumnDto;
import com.example.trello_clone.dto.card.CardCreateDto;
import com.example.trello_clone.dto.card.CardUpdateDto;
import com.example.trello_clone.dto.comment.CommentCreateDto;
import com.example.trello_clone.exceptions.GenericNotFoundException;
import com.example.trello_clone.exceptions.ValidationException;
import com.example.trello_clone.repository.auth.UserRepository;
import com.example.trello_clone.repository.card.CardRepository;
import com.example.trello_clone.validators.base.AbstractValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;



@Component
@RequiredArgsConstructor
public class CardValidator extends AbstractValidator<CardCreateDto, CardUpdateDto, Long> {
    private final BoardColumnValidator boardColumnValidator;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Override
    public void validateKey(Long id) throws ValidationException {
        Card card = getCard(id);
        boardColumnValidator.validateKey(card.getBoardColumn().getId());
    }

    private Card getCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException("Card not found by id: %s".formatted(id)));
        if (card.getIsDeleted())
            throw new ValidationException("Card is not available with id: %s".formatted(id));
        return card;
    }

    @Override
    public void validOnCreate(CardCreateDto dto) throws ValidationException {
        boardColumnValidator.validateKey(dto.getBoardColumnId());
        String name = dto.getName();
        if (Objects.isNull(name) || name.isBlank())
            throw new ValidationException("Name can not be empty");

    }

    @Override
    public void validOnUpdate(CardUpdateDto dto) throws ValidationException {

    }

    public void validateOnAddMember(CardAddMemberDto dto) {
        validateKey(dto.getId());
        AuthUser user = userRepository.findByEmail(dto.getMemberEmail())
                .orElseThrow(() -> new GenericNotFoundException("user not found by email: " + dto.getMemberEmail()));
        Workspace workspaceByCard = cardRepository.findWorkspaceByCard(dto.getId());
        if (!workspaceByCard.getMembers().contains(user))
            throw new ValidationException("You can't add member outside your workspace");
    }

    public void validateOnAddComment(CommentCreateDto dto) {
        validateKey(dto.getCardId());
        String text = dto.getText();
        if (Objects.isNull(text) || text.isBlank())
            throw new ValidationException(
                    "Comment text can not be empty"
            );
    }


    public void validateOnChangeColumn(CardChangeColumnDto dto) {
        validateKey(dto.getId());
        Card card = getCard(dto.getId());
        Board board = card.getBoardColumn().getBoard();
        Long boardColumnId = dto.getBoardColumnId();
        BoardColumn boardColumn = boardColumnValidator.getBoardColumn(boardColumnId);
        boolean isColumnFromSameBoard = board.getBoardColumns().contains(boardColumn);
        if (!isColumnFromSameBoard) {
            throw new ValidationException(
                    "Board not contains column with id: " + boardColumnId
            );
        }
    }

    public void validateOnDelete(Long id) {
        Card card = getCard(id);
        boardColumnValidator.validateOnDelete(card.getBoardColumn().getId());
    }
}
