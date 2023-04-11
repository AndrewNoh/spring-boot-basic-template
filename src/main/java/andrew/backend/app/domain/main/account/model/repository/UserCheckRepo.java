package andrew.backend.app.domain.main.account.model.repository;

import andrew.backend.app.domain.main.account.model.entity.UserCheckEntity;
import andrew.backend.app.domain.main.account.model.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCheckRepo extends JpaRepository<UserCheckEntity, Long> {
    UserCheckEntity findByUser(UserInfoEntity user);
    UserCheckEntity findByUser_Email(String email);
}