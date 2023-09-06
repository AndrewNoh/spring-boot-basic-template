package andrew.backend.app.domain.admin.blame.controller;

import andrew.backend.app.domain.admin.blame.model.dto.RequestBlameDto;
import andrew.backend.app.domain.admin.blame.service.BlameService;
import andrew.backend.app.global.security.auth.PrincipalDetails;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@Tag(name = "Blame", description = "신고 API")
@RestController
@RequiredArgsConstructor
public class BlameController {
    private final ResponseService resp;
    private final BlameService blameService;

    @PostMapping("blame")
    @Tag(name = "Blame")
    @Operation(summary = "신고하기", description = "요구 값 다 보내주시면 됩니다.")
    @ApiResponse(code = 200, message = "SUCCESS")
    public CommonResponse addBlame(@RequestBody RequestBlameDto req, @ApiIgnore Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        if(blameService.addBlame(req, principal.getUserInfo()))return resp.defaultSuccessResponse();
        else return resp.setFailResponse(400, "신고에 실패하였습니다.");
    }
}
