package com.example.trello_clone.service.board;


import com.example.trello_clone.dto.board.BoardChangeVisibilityDto;
import com.example.trello_clone.dto.board.BoardCreateDto;
import com.example.trello_clone.dto.board.BoardDto;

public interface BoardService {
    BoardDto save(BoardCreateDto dto);

    BoardDto get(Long id);

    void changeVisibility(BoardChangeVisibilityDto dto);

    void deleteBoard(Long id);
}
