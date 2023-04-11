package andrew.backend.app.global.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import andrew.backend.app.domain.main.account.model.entity.UserCheckEntity;

@RequiredArgsConstructor
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

    private final PrincipalDetailsService principalDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserCheckEntity userCheck = principalDetailsService.findUserCheckByEmail(authentication.getName());

        // 계정 잠금 체크
        principalDetailsService.checkUserLock(userCheck);
        try {
            // 인증 성공시
            return super.authenticate(authentication);
        } catch (BadCredentialsException e) {
            // 인증 실패시 비밀번호 오류 처리
            int remainingAttempts = principalDetailsService.updateUserCheckCount(authentication.getName());
            String message = String.format("count:%d", remainingAttempts);
            throw new BadCredentialsException(message);
        }
    }
}