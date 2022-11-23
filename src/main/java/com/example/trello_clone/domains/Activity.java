package com.example.trello_clone.domains;

import com.example.trello_clone.domains.auth.AuthUser;
import com.example.trello_clone.domains.board.Board;
import lombok.*;

import javax.persistence.*;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AuthUser user;
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
    private String description;
}
