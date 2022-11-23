package com.example.trello_clone.repository.boardColumn;


import com.example.trello_clone.domains.board.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BoardColumnRepository extends JpaRepository<BoardColumn, Long> {
}
