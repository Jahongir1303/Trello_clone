package com.example.trello_clone.domains;

import com.example.trello_clone.domains.auth.AuthUser;
import com.example.trello_clone.domains.board.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = AuthUser.class)
    @JoinColumn(nullable = false, referencedColumnName = "id", name = "created_by")
    private AuthUser creator;
    @Column(nullable = false)
    private String text;
    private String attachment;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false, name = "card_id", referencedColumnName = "id")
    private Card card;
}
