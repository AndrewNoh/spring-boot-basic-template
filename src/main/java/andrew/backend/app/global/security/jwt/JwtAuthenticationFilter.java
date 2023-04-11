package andrew.backend.app.global.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import andrew.backend.app.domain.main.account.model.dto.LoginRequestDto;
import andrew.backend.app.domain.main.account.model.dto.TokenDto;
import andrew.backend.app.domain.main.account.model.entity.TokenEntity;
import andrew.backend.app.domain.main.account.model.entity.UserInfoEntity;
import andrew.backend.app.domain.main.account.model.enums.SnsProvider;
import andrew.backend.app.domain.main.account.model.repository.TokenRepo;
import andrew.backend.app.global.response.CommonResponse;
import andrew.backend.app.global.security.auth.PrincipalDetails;
import andrew.backend.app.global.security.auth.PrincipalDetailsService;
import andrew.backend.app.global.security.sns.SnsTokenVerifier;
import andrew.backend.app.global.util.JwtPrivateKey;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static andrew.backend.app.domain.main.account.model.dto.LoginRequestDto.*;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final String TOKEN_PREFIX = JwtPrivateKey.TOKEN_PREFIX;
    private final int EXPIRATION_TIME = JwtPrivateKey.EXPIRATION_TIME;
    private final String SECRET = JwtPrivateKey.SECRET;
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final SnsTokenVerifier snsTokenVerifier;
    private final PrincipalDetailsService principalDetailsService;
    private final TokenRepo tokenRepo;
    //private final AmazonElastiCache elasticache;


    // Authentication 객체 만들어서 리턴 => 의존 : AuthenticationManager
    // 인증 요청시에 실행되는 함수 => /login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        System.out.println("JwtAuthenticationFilter : 진입");

        // request에 있는 username과 password를 파싱해서 자바 Object로 받기
        LoginRequestDto loginRequest;
        UsernamePasswordAuthenticationToken authenticationToken;
        try {
            loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequestDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Invalid LoginRequest: " + e.getMessage());
        }
        if (loginRequest.getEmailLogin() != null) {
            LoginEmailDto emailDto = loginRequest.getEmailLogin();
            authenticationToken = new UsernamePasswordAuthenticationToken(emailDto.getEmail(), emailDto.getPassword());
        } else {
            LoginSocialDto socialDto = loginRequest.getSocialLogin();
            SnsProvider provider = socialDto.getProvider();
            String accessToken = socialDto.getAccessToken();
            String snsId = snsTokenVerifier.getSnsIdFromToken(provider, accessToken);
            String email = principalDetailsService.loadUserBySnsIdAndProvider(snsId, provider);
            authenticationToken = new UsernamePasswordAuthenticationToken(email, SECRET);
        }

        // authenticate() 함수가 호출 되면 인증 프로바이더가 유저 디테일 서비스의
        // loadUserByUsername(토큰의 첫번째 파라메터) 를 호출하고
        // UserDetails를 리턴받아서 토큰의 두번째 파라메터(credential)과
        // UserDetails(DB값)의 getPassword()함수로 비교해서 동일하면
        // Authentication 객체를 만들어서 필터체인으로 리턴해준다.

        // Tip: 인증 프로바이더의 디폴트 서비스는 UserDetailsService 타입
        // Tip: 인증 프로바이더의 디폴트 암호화 방식은 BCryptPasswordEncoder
        // 결론은 인증 프로바이더에게 알려줄 필요가 없음.
        return authenticationManager.authenticate(authenticationToken);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        UserInfoEntity userInfo = principalDetails.getUserInfo();

        //유저 비밀번호 오류 초기화
        principalDetailsService.resetUserCheckCount(userInfo);

        // 기존에 발급된 토큰들을 모두 만료시킴
        tokenRepo.updateExpiredTokens(userInfo);

        // 새로운 토큰을 발급하여 저장
        TokenEntity newToken = new TokenEntity(userInfo, TokenUtil.generateJwtToken(principalDetails, EXPIRATION_TIME, SECRET), false);
        tokenRepo.save(newToken);

        TokenDto tokenDto = new TokenDto(TOKEN_PREFIX + newToken.getToken());
        String result = objectMapper.writeValueAsString(tokenDto);
        response.getWriter().write(result);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        CommonResponse resp = new CommonResponse();
        resp.setSuccess(false);
        if (exception instanceof BadCredentialsException) {
            resp.setCode(3303); // Unauthorized
            resp.setMessage(exception.getMessage());
        } else if (exception instanceof InternalAuthenticationServiceException) {
            resp.setCode(500); // Internal Server Error
            resp.setMessage("System Error");
        } else if (exception instanceof UsernameNotFoundException) {
            resp.setCode(404); // Not Found
            resp.setMessage("UsernameNotFoundException");
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            resp.setCode(401); // Unauthorized
            resp.setMessage("AuthenticationCredentialsNotFoundException");
        } else if (exception instanceof LockedException) {
            resp.setCode(3302); // Locked
            resp.setMessage("LockedException");
        } else {
            resp.setCode(404); // Not Found
            resp.setMessage("Not Found Error");
        }

        response.getWriter().write(objectMapper.writeValueAsString(resp));
    }
}