package com.example.trello_clone.domains.board;



import com.example.trello_clone.domains.workspace.Workspace;
import com.example.trello_clone.enums.board.BoardVisibilityType;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
//@SqlResultSetMapping(
//        name = "BoardToBoardDTOMapping",
//        classes = @ConstructorResult(
//                targetClass = BoardDto.class,
//                columns = {
//
//                }
//        )
//)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private BoardVisibilityType visibilityType;

    @Builder.Default
    @OneToMany(mappedBy = "board")
    private Set<BoardColumn> boardColumns = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", nullable = false, referencedColumnName = "id")
    private Workspace workspace;

    @Builder.Default
    @Column(columnDefinition = "bool default false")
    private Boolean isDeleted = false;
}
