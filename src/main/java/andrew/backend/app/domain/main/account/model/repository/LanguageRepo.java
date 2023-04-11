package andrew.backend.app.domain.main.account.model.repository;

import andrew.backend.app.domain.main.account.model.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepo extends JpaRepository<LanguageEntity, Long> {
    LanguageEntity findByLanguageCode(String languageCode);
}