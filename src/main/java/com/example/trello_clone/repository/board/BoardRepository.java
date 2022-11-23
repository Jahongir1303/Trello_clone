package com.example.trello_clone.repository.board;


import com.example.trello_clone.domains.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

//    @Query("select t.id,t.name,t.visibilityType,t.workspace.id from Board t where t.id=1l")
//    Optional<BoardDto> getBoardDto();
//    @Query("select t from Board t inner join t.boardColumns d where d.isDeleted=false")
//    Optional<Board> findById(Long id);
}
