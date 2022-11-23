package com.example.trello_clone.service.boardColumn;

import com.example.trello_clone.dto.boardColumn.BoardColumnCreateDto;
import com.example.trello_clone.dto.boardColumn.BoardColumnDto;
import com.example.trello_clone.dto.boardColumn.BoardColumnOrderChangeDto;

public interface BoardColumnService {
    BoardColumnDto save(BoardColumnCreateDto dto);

    BoardColumnDto changeOrder(BoardColumnOrderChangeDto dto);

    void deleteColumn(Long id);
}
