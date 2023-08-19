package cri.vaycompany.linkserve.repositories;

import cri.vaycompany.linkserve.models.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserConnectionRepository extends JpaRepository<UserConnection, Long> {
    List<UserConnection> findByUserOneIdOrUserTwoId(Long userOneId, Long userTwoId);
}