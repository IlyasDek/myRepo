package cri.vaycompany.linkserve.repositories;

import cri.vaycompany.linkserve.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
}
