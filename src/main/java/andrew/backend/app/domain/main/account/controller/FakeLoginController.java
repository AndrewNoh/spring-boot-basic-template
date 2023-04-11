package andrew.backend.app.domain.main.account.controller;

import andrew.backend.app.domain.main.account.model.dto.LoginRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Login", description = "로그인 API")
public class FakeLoginController {

    @PostMapping("/login")
    @Operation(summary = "소셜/일반 로그인", description = "접근 방식의 DTO 값 넘겨주시면 됩니다. 현재 어드민은 안됩니다.")
    @Tag(name = "Login")
    public void fakeLogin(@RequestBody LoginRequestDto loginRequestDto) {
        throw new UnsupportedOperationException("This method is implemented by Spring Security filters and should not be called directly.");
    }
}
