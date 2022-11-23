package com.example.trello_clone.repository.workspace;


import com.example.trello_clone.domains.auth.AuthUser;
import com.example.trello_clone.domains.workspace.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    @Query(value = "select t from Workspace t where (t.createdBy =:user or :user member of t.members) and t.isDeleted=false")
    List<Workspace> findAllByUser(AuthUser user);
}
