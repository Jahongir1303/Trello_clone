package com.example.trello_clone.service.board;


import com.example.trello_clone.domains.board.Board;
import com.example.trello_clone.domains.workspace.Workspace;
import com.example.trello_clone.dto.board.BoardChangeVisibilityDto;
import com.example.trello_clone.dto.board.BoardCreateDto;
import com.example.trello_clone.dto.board.BoardDto;
import com.example.trello_clone.exceptions.GenericNotFoundException;
import com.example.trello_clone.mappers.board.BoardMapper;
import com.example.trello_clone.repository.board.BoardRepository;
import com.example.trello_clone.repository.workspace.WorkspaceRepository;
import com.example.trello_clone.validators.board.BoardValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardValidator boardValidator;
    private final BoardRepository boardRepository;
    private final WorkspaceRepository workspaceRepository;
    private final BoardMapper boardMapper;

    @Override
    public BoardDto save(BoardCreateDto dto) {
        boardValidator.validOnCreate(dto);
        Board board = boardMapper.fromCreateDto(dto);
        Workspace workspace = workspaceRepository.findById(dto.getWorkspaceId())
                .orElseThrow(() -> new GenericNotFoundException("Workspace not found"));
        board.setWorkspace(workspace);
        Board savedBoard = boardRepository.save(board);
        return boardMapper.fromBoard(savedBoard);
    }

    @Override
    public BoardDto get(Long id) {
        boardValidator.validateKey(id);
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException("Board not found by id : %s".formatted(id)));
        return boardMapper.fromBoard(board);
    }

    @Override
    public void changeVisibility(BoardChangeVisibilityDto dto) {
        boardValidator.validateOnChangeVisibility(dto);
        Board board = boardRepository.findById(dto.getId())
                .orElseThrow(() -> new GenericNotFoundException("Board not found by id : %s".formatted(dto.getId())));

        board.setVisibilityType(dto.getVisibility());
        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long id) {
        boardValidator.validateOnDelete(id);
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException(
                        "Board not found by id : %s".formatted(id))
                );

        board.setIsDeleted(true);
        boardRepository.save(board);
    }
}
