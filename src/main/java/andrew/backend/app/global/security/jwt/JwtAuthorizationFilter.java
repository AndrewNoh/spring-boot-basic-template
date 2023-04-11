package andrew.backend.app.global.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import andrew.backend.app.domain.main.account.model.dto.UserEntityRoleDto;
import andrew.backend.app.domain.main.account.model.entity.TokenEntity;
import andrew.backend.app.domain.main.account.model.entity.UserInfoEntity;
import andrew.backend.app.domain.main.account.model.enums.Roles;
import andrew.backend.app.domain.main.account.model.repository.TokenRepo;
import andrew.backend.app.domain.main.account.service.AccountService;
import andrew.backend.app.global.security.auth.PrincipalDetails;
import andrew.backend.app.global.util.JwtPrivateKey;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final AccountService userService;
    private final TokenRepo tokenRepo;
    public String HEADER_STRING = JwtPrivateKey.HEADER_STRING;
    public String TOKEN_PREFIX = JwtPrivateKey.TOKEN_PREFIX;
    int EXPIRATION_TIME = JwtPrivateKey.EXPIRATION_TIME;
    public String SECRET = JwtPrivateKey.SECRET;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AccountService userService, TokenRepo tokenRepo) {
        super(authenticationManager);
        this.userService = userService;
        this.tokenRepo = tokenRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        System.out.println("doFilterInternal 진입");

        String token = header.replace(TOKEN_PREFIX, "");
        DecodedJWT decodedJWT = TokenUtil.verifyJwtToken(token, SECRET);
        String email = decodedJWT.getClaim("email").asString();

        if (email == null) {
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        // 사용자 정보와 권한 정보 가져오기
        UserEntityRoleDto userEntityRoleDto = userService.getRolesUserEntity(email);
        UserInfoEntity userInfo = userEntityRoleDto.getUserInfo();
        List<Roles> authorities = userEntityRoleDto.getRoles();

        // 정지된 회원 또는 탈퇴한 회원인 경우 처리
        if (authorities.contains(Roles.ROLE_SUSPENDED)) {
            sendErrorResponse(response, HttpServletResponse.SC_FORBIDDEN, "정지된 회원입니다.");
            return;
        } else if (authorities.contains(Roles.ROLE_WITHDRAWN)) {
            sendErrorResponse(response, HttpServletResponse.SC_FORBIDDEN, "탈퇴한 회원입니다.");
            return;
        }

        // 현재 사용중인 토큰인지 확인
        List<TokenEntity> tokens = tokenRepo.findByUserAndExpiredFalse(userInfo);
        boolean isValidToken = tokens.stream().map(TokenEntity::getToken).collect(Collectors.toList()).contains(token);

        if (!isValidToken) {
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        // 토큰 만료 시 처리
        if (decodedJWT.getExpiresAt().before(new Date())) {
            tokenRepo.updateExpiredTokens(userInfo);
            TokenEntity newToken = tokenRepo.save(new TokenEntity(userInfo, TokenUtil.generateJwtToken(getPrincipalDetails(userInfo, authorities), EXPIRATION_TIME, SECRET), false));
            response.setHeader(HEADER_STRING, TOKEN_PREFIX + newToken.getToken());
        }
        System.out.println("decodedJWT 진입");

        // 인증 처리
        setAuthentication(getPrincipalDetails(userInfo, authorities));
        chain.doFilter(request, response);
    }

    // 에러 응답 처리
    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write(String.format("{\"error\": \"%s\"}", message));
    }

    // PrincipalDetails 객체 생성
    private PrincipalDetails getPrincipalDetails(UserInfoEntity userInfo, List<Roles> authorities) {
        return new PrincipalDetails(userInfo, authorities);
    }

    // 인증 처리
    private void setAuthentication(PrincipalDetails principalDetails) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
