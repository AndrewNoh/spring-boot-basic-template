package andrew.backend.app.global.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    private final Server serverLocal = new Server("local", "http://localhost:8080", "for local usages", Collections.emptyList(), Collections.emptyList());
    private final Server baseServer = new Server("test", "https://api.andrew.backend.aop", "base server", Collections.emptyList(), Collections.emptyList());
    // 접속 주소
    // http://localhost:8080/swagger-ui/index.html

    //2023-03-21T00:00:00Z
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .servers(baseServer, serverLocal)
                .groupName("user-page")
                .securityContexts(Collections.singletonList(securityContext())) // 추가
                .securitySchemes(List.of(apiKey())) // 추가
                .select()
                .apis(RequestHandlerSelectors.basePackage("andrew.backend.app.domain.main"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket admin() {
        return new Docket(DocumentationType.OAS_30)
                .servers(baseServer, serverLocal)
                .groupName("admin-page")
                .securityContexts(Collections.singletonList(securityContext())) // 추가
                .securitySchemes(List.of(apiKey())) // 추가
                .select()
                .apis(RequestHandlerSelectors.basePackage("andrew.backend.app.domain.admin"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket common() {
        return new Docket(DocumentationType.OAS_30)
                .servers(baseServer, serverLocal)
                .groupName("common-page")
                .securityContexts(Collections.singletonList(securityContext())) // 추가
                .securitySchemes(List.of(apiKey())) // 추가
                .select()
                .apis(RequestHandlerSelectors.basePackage("andrew.backend.app.domain.common"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    // 추가
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    // 추가
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("Authorization", authorizationScopes));
    }

    // 추가
    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Backend API")
                .description("Backend API 문서")
                .version("1.0")
                .build();
    }
}