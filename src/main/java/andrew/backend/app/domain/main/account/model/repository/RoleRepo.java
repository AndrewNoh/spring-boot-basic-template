package andrew.backend.app.domain.main.account.model.repository;

import andrew.backend.app.domain.main.account.model.entity.RoleEntity;
import andrew.backend.app.domain.main.account.model.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(Roles name);
}