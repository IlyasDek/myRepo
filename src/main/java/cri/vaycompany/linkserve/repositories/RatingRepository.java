package cri.vaycompany.linkserve.repositories;

import cri.vaycompany.linkserve.models.Rating;
import cri.vaycompany.linkserve.models.ServiceOffer;
import cri.vaycompany.linkserve.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserAndServiceOffer(User user, ServiceOffer serviceOffer);

}

