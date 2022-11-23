package com.example.trello_clone.domains.workspace;

import com.example.trello_clone.domains.auth.AuthUser;
import com.example.trello_clone.domains.board.Board;
import com.example.trello_clone.enums.workspace.WorkspaceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, referencedColumnName = "id", name = "created_by")
    private AuthUser createdBy;

    @Enumerated(EnumType.STRING)
    private WorkspaceType type;

    private String description;

    @Builder.Default
    private Boolean isVisible = true;

    @Builder.Default
    @OneToMany(mappedBy = "workspace", fetch = FetchType.LAZY)
    private Set<Board> boards = new HashSet<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(joinColumns = @JoinColumn(name = "workspace_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<AuthUser> members = new HashSet<>();

    @Builder.Default
    @Column(columnDefinition = "bool default false")
    private Boolean isDeleted = false;
}
