package com.example.trello_clone.controller;

import com.example.trello_clone.dto.card.CardAddMemberDto;
import com.example.trello_clone.dto.card.CardChangeColumnDto;
import com.example.trello_clone.dto.card.CardCreateDto;
import com.example.trello_clone.dto.card.CardDto;
import com.example.trello_clone.dto.comment.CommentCreateDto;
import com.example.trello_clone.service.card.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
@PreAuthorize("isAuthenticated()")
public class CardController {
    private final CardService cardService;

    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CardDto> createBoardColumn(@RequestBody CardCreateDto dto) {
        CardDto cardDto = cardService.save(dto);
        return new ResponseEntity<>(cardDto, HttpStatus.CREATED);
    }

    @PostMapping(value = "/addMember")
    public ResponseEntity<CardDto> addMember(@RequestBody CardAddMemberDto dto) {
        CardDto cardDto = cardService.addMember(dto);
        return new ResponseEntity<>(cardDto, HttpStatus.OK);
    }

    @PostMapping(value = "/comment/create")
    public ResponseEntity<CardDto> addComment(@RequestBody CommentCreateDto dto) {
        CardDto cardDto = cardService.addComment(dto);
        return new ResponseEntity<>(cardDto, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/changeColumn")
    public ResponseEntity<CardDto> changeColumn(@RequestBody CardChangeColumnDto dto) {
        CardDto cardDto = cardService.changeColumn(dto);
        return new ResponseEntity<>(cardDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
