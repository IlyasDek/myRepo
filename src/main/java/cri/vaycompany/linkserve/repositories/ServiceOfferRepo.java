package cri.vaycompany.linkserve.repositories;

import cri.vaycompany.linkserve.models.ServiceOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOfferRepo extends JpaRepository<ServiceOffer, Long> {
}
