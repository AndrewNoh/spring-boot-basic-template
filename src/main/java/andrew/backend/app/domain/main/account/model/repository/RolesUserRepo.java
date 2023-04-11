package andrew.backend.app.domain.main.account.model.repository;

import andrew.backend.app.domain.main.account.model.entity.RolesUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesUserRepo extends JpaRepository<RolesUserEntity, Long> {
}