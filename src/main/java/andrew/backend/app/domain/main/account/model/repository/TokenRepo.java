package andrew.backend.app.domain.main.account.model.repository;

import andrew.backend.app.domain.main.account.model.entity.TokenEntity;
import andrew.backend.app.domain.main.account.model.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TokenRepo extends JpaRepository<TokenEntity, Long> {
    List<TokenEntity> findByUserAndExpiredFalse(UserInfoEntity user);
    @Transactional
    @Modifying
    @Query("UPDATE TokenEntity t SET t.expired = true WHERE t.user = :user AND t.expired = false")
    void updateExpiredTokens(@Param("user") UserInfoEntity user);

}

