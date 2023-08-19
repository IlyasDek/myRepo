package cri.vaycompany.linkserve.repositories;

import cri.vaycompany.linkserve.models.ProviderConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderConnectionRepository extends JpaRepository<ProviderConnection, Long> {
    List<ProviderConnection> findByProviderOneIdOrProviderTwoId(Long providerOneId, Long providerTwoId);
}

