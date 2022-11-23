package com.example.trello_clone.repository.card;


import com.example.trello_clone.domains.board.Card;
import com.example.trello_clone.domains.workspace.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("select w from Card c join c.boardColumn t join t.board b join b.workspace w where c.id=:cardId")
    Workspace findWorkspaceByCard(Long cardId);
}
