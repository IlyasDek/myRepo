package cri.vaycompany.linkserve.config.security.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(Integer id);

    @Query(value = """
      select t from Token t inner join Provider p\s
      on t.user.id = p.id\s
      where p.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByProvider(Integer id);

    Optional<Token> findByToken(String token);
}


