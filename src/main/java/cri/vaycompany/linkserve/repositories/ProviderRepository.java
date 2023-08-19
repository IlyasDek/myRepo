package cri.vaycompany.linkserve.repositories;

import cri.vaycompany.linkserve.models.Provider;
import cri.vaycompany.linkserve.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findByEmail(String email);
    Optional<Provider> findByPhoneNumber(String phoneNumber);
    Optional<Provider> findByName(String name);
}