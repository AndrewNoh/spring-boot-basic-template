package andrew.backend.app.domain.admin.blame.model.repository;

import andrew.backend.app.domain.admin.blame.model.entity.AdminBlameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminBlameRepo extends JpaRepository<AdminBlameEntity, Long> {
}
