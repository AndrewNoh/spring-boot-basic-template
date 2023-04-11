package andrew.backend.app.domain.main.account.model.repository;

import andrew.backend.app.domain.main.account.model.entity.SnsInfoEntity;
import andrew.backend.app.domain.main.account.model.enums.SnsProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SnsInfoRepo extends JpaRepository<SnsInfoEntity, Long> {
    Optional<SnsInfoEntity> findByProviderAndAccessId(SnsProvider provider, String accessId);
}