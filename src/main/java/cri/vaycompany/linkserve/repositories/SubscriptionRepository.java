package cri.vaycompany.linkserve.repositories;

import cri.vaycompany.linkserve.models.Provider;
import cri.vaycompany.linkserve.models.Subscription;
import cri.vaycompany.linkserve.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);
    Subscription findByUserAndProvider(User user, Provider provider);
    List<Subscription> findByProviderId(Long providerId);
}

