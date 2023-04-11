package andrew.backend.app.domain.admin.onboarding.model.repository;

import andrew.backend.app.domain.admin.onboarding.model.entity.AdminOnboardingEntity;
import andrew.backend.app.domain.main.account.model.entity.CountryCodeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminOnboardingRepo extends JpaRepository<AdminOnboardingEntity, Long> {
    List<AdminOnboardingEntity> findByCountryCodeAndStatusTrueOrderBySequence(CountryCodeEntity countryCodeEntity);

    @Query("SELECT ao.sequence FROM AdminOnboardingEntity ao JOIN ao.countryCode cc WHERE cc.codeId = :codeId ORDER BY ao.sequence DESC")
    List<Integer> findLastSequenceByCountryCodeId(@Param("codeId") Long codeId, Pageable pageable);
}
