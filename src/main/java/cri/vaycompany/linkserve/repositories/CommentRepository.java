package cri.vaycompany.linkserve.repositories;

import cri.vaycompany.linkserve.models.Comment;
import cri.vaycompany.linkserve.models.ServiceOffer;
import cri.vaycompany.linkserve.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByServiceOfferId(Long serviceOfferId);
    List<Comment> findByUserId(Long userId);
    boolean existsByUserIdAndServiceOfferId(Long userId, Long serviceOfferId);

}