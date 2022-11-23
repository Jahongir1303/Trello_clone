package com.example.trello_clone.service.card;



import com.example.trello_clone.domains.Comment;
import com.example.trello_clone.domains.auth.AuthUser;
import com.example.trello_clone.domains.board.BoardColumn;
import com.example.trello_clone.domains.board.Card;
import com.example.trello_clone.dto.card.CardAddMemberDto;
import com.example.trello_clone.dto.card.CardChangeColumnDto;
import com.example.trello_clone.dto.card.CardCreateDto;
import com.example.trello_clone.dto.card.CardDto;
import com.example.trello_clone.dto.comment.CommentCreateDto;
import com.example.trello_clone.exceptions.GenericNotFoundException;
import com.example.trello_clone.mappers.board.CardMapper;
import com.example.trello_clone.mappers.board.CommentMapper;
import com.example.trello_clone.repository.auth.UserRepository;
import com.example.trello_clone.repository.boardColumn.BoardColumnRepository;
import com.example.trello_clone.repository.card.CardRepository;
import com.example.trello_clone.validators.board.CardValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardValidator cardValidator;
    private final CardMapper cardMapper;
    private final BoardColumnRepository boardColumnRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public CardDto save(CardCreateDto dto) {
        cardValidator.validOnCreate(dto);
        Card card = cardMapper.fromCreateDto(dto);
        BoardColumn boardColumn = boardColumnRepository.findById(dto.getBoardColumnId())
                .orElseThrow(() -> new GenericNotFoundException("Board column not found by id: " + dto.getBoardColumnId()));
        card.setBoardColumn(boardColumn);
        return cardMapper.fromCard(cardRepository.save(card));
    }

    @Override
    public CardDto addComment(CommentCreateDto dto) {
        cardValidator.validateOnAddComment(dto);
        Comment comment = commentMapper.fromCreateDto(dto);
        AuthUser authUser = userRepository.findByEmail(dto.getCreatorEmail())
                .orElseThrow(() -> new GenericNotFoundException("User not found by email: " + dto.getCreatorEmail()));
        comment.setCreator(authUser);
        Long cardId = dto.getCardId();
        Card card = getCard(cardId);
        comment.setCard(card);
        card.getComments().add(comment);
        return cardMapper.fromCard(cardRepository.save(card));
    }

    private Card getCard(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new GenericNotFoundException("Card not found by id: " + cardId));
        return card;
    }

    @Override
    public CardDto addMember(CardAddMemberDto dto) {
        cardValidator.validateOnAddMember(dto);
        Card card = getCard(dto.getId());
        AuthUser authUser = userRepository.findByEmail(dto.getMemberEmail())
                .orElseThrow(() -> new GenericNotFoundException("user not found by email: " + dto.getMemberEmail()));
        card.getMembers().add(authUser);
        return cardMapper.fromCard(cardRepository.save(card));
    }

    @Override
    public CardDto changeColumn(CardChangeColumnDto dto) {
        cardValidator.validateOnChangeColumn(dto);
        Card card = getCard(dto.getId());
        BoardColumn boardColumn = boardColumnRepository.findById(dto.getBoardColumnId())
                .orElseThrow(() -> new GenericNotFoundException("Board column not found by id: " + dto.getBoardColumnId()));
        card.setBoardColumn(boardColumn);
        return cardMapper.fromCard(cardRepository.save(card));
    }

    @Override
    public void deleteCard(Long id) {
        cardValidator.validateOnDelete(id);
        Card card = getCard(id);
        card.setIsDeleted(true);
        cardRepository.save(card);
    }
}
