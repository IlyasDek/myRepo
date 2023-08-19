package cri.vaycompany.linkserve.repositories;

import cri.vaycompany.linkserve.models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {}

