package andrew.backend.app.domain.main.account.model.repository;

import andrew.backend.app.domain.main.account.model.entity.UserInfoEntity;
import andrew.backend.app.domain.main.account.model.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfoEntity, Long> {
    Optional<UserInfoEntity> findByEmail(String email);

    @Query("SELECT r.name FROM RolesUserEntity ru JOIN ru.roleEntity r WHERE ru.userInfo.userId = :userId")
    List<Roles> findRoleNamesByUsername(@Param("userId") Long userId);

    //시작일과 마감일 기간 동안 총 가입자 수
    @Query("SELECT COUNT(u) FROM UserInfoEntity u WHERE u.createdAt >= :startDate AND u.createdAt <= :endDate")
    Long findTotalUsersByPeriod(LocalDateTime startDate, LocalDateTime endDate);

    //해당 기간을 제외한 총 가입자 수
    @Query("SELECT COUNT(u) FROM UserInfoEntity u WHERE u.createdAt < :startDate OR u.createdAt > :endDate")
    Long findTotalUsersExcludingPeriod(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT COUNT(u) as totalUser, " +
            "(SELECT COUNT(u2) FROM UserInfoEntity u2 WHERE u2.createdAt < :startDate OR u2.createdAt > :endDate) as excludedUser " +
            "FROM UserInfoEntity u " +
            "WHERE u.createdAt BETWEEN :startDate AND :endDate")
    Map<String, Long> countUsersByDateRange(@Param("startDate") Instant startDate, @Param("endDate") Instant endDate);

}