package andrew.backend.app.global.security.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import andrew.backend.app.domain.main.account.model.repository.TokenRepo;
import andrew.backend.app.domain.main.account.service.AccountService;
import andrew.backend.app.global.security.auth.CustomDaoAuthenticationProvider;
import andrew.backend.app.global.security.auth.PrincipalDetailsService;
import andrew.backend.app.global.security.jwt.JwtAuthenticationFilter;
import andrew.backend.app.global.security.jwt.JwtAuthorizationFilter;
import andrew.backend.app.global.security.sns.SnsTokenVerifier;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AccountService accountService;
    private final TokenRepo tokenRepo;
    private final CorsConfig corsConfig;
    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PrincipalDetailsService principalDetailsService;
    private final SnsTokenVerifier snsTokenVerifier;

    private static final String[] PERMIT_All_URL_ARRAY = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/my-page/faq/**"
    };
    private static final String[] PERMIT_USER_URL_ARRAY = {
            "/my-page/**"
    };
    private static final String[] PERMIT_ADMIN_ARRAY = {
            "/admin/**"
    };

    public DaoAuthenticationProvider daoAuthenticationProvider() {
        CustomDaoAuthenticationProvider authenticationProvider = new CustomDaoAuthenticationProvider(principalDetailsService);
        authenticationProvider.setUserDetailsService(principalDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .addFilterBefore(corsConfig.corsFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtAuthenticationFilter(objectMapper, authenticationManager(), snsTokenVerifier, principalDetailsService, tokenRepo))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), accountService, tokenRepo))
                .authorizeRequests(authorize -> authorize
                        .antMatchers(PERMIT_All_URL_ARRAY).permitAll()
                        .antMatchers(PERMIT_USER_URL_ARRAY).hasAnyRole("USER", "ADMIN")
                        .antMatchers(PERMIT_ADMIN_ARRAY).hasAnyRole("ADMIN")
                        .anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(daoAuthenticationProvider());
    }
}