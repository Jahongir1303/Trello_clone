package com.example.trello_clone.controller;


import com.example.trello_clone.dto.board.BoardChangeVisibilityDto;
import com.example.trello_clone.dto.board.BoardCreateDto;
import com.example.trello_clone.dto.board.BoardDto;
import com.example.trello_clone.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@PreAuthorize("isAuthenticated()")
public class BoardController {

    private final BoardService boardService;

    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardCreateDto dto) {
        BoardDto boardDto = boardService.save(dto);
        return new ResponseEntity<>(boardDto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/get/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BoardDto> getBoard(@PathVariable Long id) {
        BoardDto boardDto = boardService.get(id);
        return ResponseEntity.ok(boardDto);
    }


    @PatchMapping(value = "/changeVisibility")
    public ResponseEntity changeVisibility(@Valid @RequestBody BoardChangeVisibilityDto dto) {
        boardService.changeVisibility(dto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
