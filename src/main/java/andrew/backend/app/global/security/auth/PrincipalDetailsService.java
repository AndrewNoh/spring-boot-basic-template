package andrew.backend.app.global.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import andrew.backend.app.domain.main.account.model.entity.SnsInfoEntity;
import andrew.backend.app.domain.main.account.model.entity.UserCheckEntity;
import andrew.backend.app.domain.main.account.model.entity.UserInfoEntity;
import andrew.backend.app.domain.main.account.model.enums.Roles;
import andrew.backend.app.domain.main.account.model.enums.SnsProvider;
import andrew.backend.app.domain.main.account.model.repository.SnsInfoRepo;
import andrew.backend.app.domain.main.account.model.repository.UserCheckRepo;
import andrew.backend.app.domain.main.account.model.repository.UserInfoRepo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserInfoRepo userInfoRepo;
    private final SnsInfoRepo snsInfoRepo;
    private final UserCheckRepo userCheckRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfoEntity userInfo = userInfoRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid email or password."));
        List<Roles> authorities = userInfoRepo.findRoleNamesByUsername(userInfo.getUserId());
        checkUserLock(findUserCheck(userInfo));
        return new PrincipalDetails(userInfo, authorities);
    }

    public String loadUserBySnsIdAndProvider(String snsId, SnsProvider provider) throws UsernameNotFoundException {
        SnsInfoEntity snsInfo = snsInfoRepo.findByProviderAndAccessId(provider, snsId).orElseThrow(() -> new UsernameNotFoundException("Invalid SNS login information."));
        UserInfoEntity userInfo = snsInfo.getUserInfo();
        // SNS 로그인 정보를 이용해 유저 정보 생성
        return userInfo.getEmail();
    }

    public int updateUserCheckCount(String email) throws LockedException {
        UserInfoEntity userInfo = userInfoRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid email or password."));
        UserCheckEntity userCheck = findUserCheck(userInfo);
        if (userCheck == null) {
            userCheck = new UserCheckEntity();
            userCheck.setUser(userInfo);
            userCheck.incrementRetryCount();
            userCheck = userCheckRepo.save(userCheck);
            return userCheck.getRetryCount();
        } else {
            if (userCheck.getRetryCount() < 5) {
                userCheck.incrementRetryCount();
                userCheck = userCheckRepo.save(userCheck);
            }
        }
        return userCheck.getRetryCount();
    }

    public void resetUserCheckCount(UserInfoEntity userInfo) {
        UserCheckEntity userCheck = findUserCheck(userInfo);
        if (userCheck != null) {
            userCheckRepo.delete(userCheck);
        }
    }

    private UserCheckEntity findUserCheck(UserInfoEntity userInfo) {
        return userCheckRepo.findByUser(userInfo);
    }

    public UserCheckEntity findUserCheckByEmail(String email) {
        return userCheckRepo.findByUser_Email(email);

    }

    public void checkUserLock(UserCheckEntity userCheck) {
        if (userCheck != null && userCheck.getRetryCount() >= 5) {
            if (Instant.now().isBefore(userCheck.getUpdatedAt().plus(30, ChronoUnit.MINUTES))) {
                throw new LockedException("Account locked due to too many login attempts.");
            } else {
                userCheckRepo.delete(userCheck);
            }
        }

    }


}