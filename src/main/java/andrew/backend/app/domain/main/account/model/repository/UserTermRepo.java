package andrew.backend.app.domain.main.account.model.repository;

import andrew.backend.app.domain.main.account.model.entity.UserTermEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTermRepo extends JpaRepository<UserTermEntity, Long> {
}