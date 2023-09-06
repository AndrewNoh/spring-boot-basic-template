package andrew.backend.app.domain.admin.onboarding.controller;

import andrew.backend.app.domain.admin.onboarding.model.dto.*;
import andrew.backend.app.domain.admin.onboarding.service.OnboardingService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Onboarding", description = "온보딩 API")
@RestController
@RequiredArgsConstructor
public class OnboardingController {

    private final ResponseService resp;
    private final OnboardingService onboardingService;

    @GetMapping("onboarding/initialize")
    @Tag(name = "Onboarding")
    @Operation(summary = "온보딩 호출", description = "최초 앱실행시 온보딩 노출")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS"), @ApiResponse(code = 406, message = "온보딩이 존재하지 않습니다.")})
    public ListResponse<ResponseOnboardingDto> showOnboarding(RequestLanguageCodeDto requestLanguageCodeDto) {
        List<ResponseOnboardingDto> onboardingDtoList = onboardingService.showOnboarding(requestLanguageCodeDto.getLanguageCode());
        return resp.getListResponse(onboardingDtoList);
    }

    @PostMapping("admin/onboarding")
    @Tag(name = "Onboarding")
    @Operation(summary = "온보딩 작성", description = "온보딩 작성하기")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS"), @ApiResponse(code = 400, message = "온보딩 작성에 실패하였습니다.")})
    public SingleResponse<ResponseOnboardingDto> writeOnboarding (@RequestBody RequestWriteOnboardingDto writeOnboardingDto) {
            return resp.getSingleResponse(onboardingService.writeOnboarding(writeOnboardingDto));
    }

    @PutMapping("admin/onboarding/title")
    @Tag(name = "Onboarding")
    @Operation(summary = "온보딩 제목", description = "타이틀 작성 & 변경")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS"), @ApiResponse(code = 400, message = "타이틀 변경에 실패하였습니다.")})
    public CommonResponse writeTitle (@RequestBody RequestOnboardingTitleDto titleDto) {
        if(onboardingService.modifyOnboarding(titleDto))
            return resp.defaultSuccessResponse();
        else
            return resp.setFailResponse(400, "온보딩 작성에 실패하였습니다.");
    }

    @PutMapping("admin/onboarding/file")
    @Tag(name = "Onboarding")
    @Operation(summary = "온보딩 첨부 이미지", description = "이미지 작성 & 변경")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS"), @ApiResponse(code = 400, message = "타이틀 변경에 실패하였습니다.")})
    public CommonResponse writeImage (@RequestBody RequestOnboardingImageDto imageDto) {
        if(onboardingService.modifyOnboarding(imageDto))
            return resp.defaultSuccessResponse();
        else
            return resp.setFailResponse(400, "온보딩 작성에 실패하였습니다.");
    }

    @PutMapping("admin/onboarding/status")
    @Tag(name = "Onboarding")
    @Operation(summary = "온보딩 활성화", description = "활성화 상태 변경")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS"), @ApiResponse(code = 400, message = "타이틀 변경에 실패하였습니다.")})
    public CommonResponse writeStatus (@RequestBody RequestOnboardingStatusDto statusDto) {
        if(onboardingService.modifyOnboarding(statusDto))
            return resp.defaultSuccessResponse();
        else
            return resp.setFailResponse(400, "온보딩 작성에 실패하였습니다.");
    }

    @PutMapping("admin/onboarding/move")
    @Tag(name = "Onboarding")
    @Operation(summary = "온보딩 순서 이동", description = "순서 이동")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS"), @ApiResponse(code = 400, message = "온보딩 순서 이동에 실패하였습니다.")})
    public CommonResponse moveOnboarding (@RequestBody RequestOnboardingMoveDto moveDto) {
        if(onboardingService.moveOnboarding(moveDto))
            return resp.defaultSuccessResponse();
        else
            return resp.setFailResponse(400, "온보딩 순서 이동에 실패하였습니다.");
    }


}
