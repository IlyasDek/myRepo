package cri.vaycompany.linkserve.repositories;

import cri.vaycompany.linkserve.models.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    List<Connection> findByUserId(Long userId);
}
