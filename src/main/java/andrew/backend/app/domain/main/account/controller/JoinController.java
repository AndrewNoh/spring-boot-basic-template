package andrew.backend.app.domain.main.account.controller;

import andrew.backend.app.domain.admin.onboarding.model.dto.RequestLanguageCodeDto;
import andrew.backend.app.domain.main.account.model.dto.join.*;
import andrew.backend.app.domain.main.account.model.entity.UserInfoEntity;
import andrew.backend.app.domain.main.account.service.AccountService;
import andrew.backend.app.domain.main.account.service.MailService;
import andrew.backend.app.global.configuration.TwilioSmsSender;
import andrew.backend.app.global.response.CommonResponse;
import andrew.backend.app.global.response.ResponseService;
import andrew.backend.app.global.response.SingleResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import java.util.Optional;

/*
Create: 데이터 생성 (POST)
Read: 데이터 조회 (GET)
Update: 데이터 수정 (PUT)
Delete: 데이터 삭제 (DELETE)
 */

@Tag(name = "Account", description = "회원가입 API")
@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class JoinController {

    private final AccountService accountService;
    private final ResponseService resp;
    private final TwilioSmsSender twilioSmsSender;
    private final MailService mailService;

    //회원 가입
    @PostMapping("normal-join")
    @Tag(name = "Account")
    @Operation(summary = "일반 회원가입", description = "Dto 값 전부 필수입니다.")
    @ApiResponse(code = 200, message = "SUCCESS")
    public CommonResponse normalJoin(@RequestBody NormalJoinDto normalJoinDto) {
        if(accountService.normalJoin(normalJoinDto))
            return resp.defaultSuccessResponse();
        else
            return resp.setFailResponse(400, "회원가입에 실패하였습니다.");
    }

    //회원 가입
    @PostMapping("social-join")
    @Tag(name = "Account")
    @Operation(summary = "소셜 회원가입", description = "ENUM 값 확인이후 userType 보내주시면됩니다.")
    @ApiResponse(code = 200, message = "SUCCESS")
    public CommonResponse socialJoin(@RequestBody SocialJoinDto socialJoinDto) {
        if(accountService.socialJoin(socialJoinDto))
            return resp.defaultSuccessResponse();
        else
            return resp.setFailResponse(400, "회원가입에 실패하였습니다.");
    }

    @GetMapping("email-address")
    @Tag(name = "Account")
    @Operation(summary = "이메일 중복 체크", description = "이메일 중복 체크 입니다.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS"), @ApiResponse(code = 406, message = "이미 존재하는 이메일입니다.")})
    public CommonResponse findSchoolName(CheckEmailDto checkEmailDto) {
        Optional<UserInfoEntity> userInfo = accountService.findByEmail(checkEmailDto.getEmail());
        if (userInfo.isEmpty()) return resp.defaultSuccessResponse();
        else return resp.setFailResponse(HttpStatus.NOT_ACCEPTABLE.value(), "이미 존재하는 이메일입니다.");
    }

    @GetMapping("terms-country")
    @Tag(name = "Account")
    @Operation(summary = "회원가입 필요정보", description = "이용약관 및 국가코드")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS"), @ApiResponse(code = 406, message = "이미 존재하는 이메일입니다.")})
    public SingleResponse<TermsCountryCodeDto> callDefault(RequestLanguageCodeDto requestLanguageCodeDto) {
        return resp.getSingleResponse(accountService.callDefault(requestLanguageCodeDto.getLanguageCode()));
    }

    @PostMapping("phone-number")
    @Tag(name = "Account")
    @Operation(summary = "휴대폰번호 인증", description = "ENUM 값 확인이후 userType 보내주시면됩니다.")
    @ApiResponse(code = 200, message = "SUCCESS")
    public SingleResponse<String> confirmPhone(@RequestBody RequestPhoneNumberDto req) {
            return resp.getSingleResponse(twilioSmsSender.sendSms(req));
    }

    @PostMapping("email")
    @Tag(name = "Account")
    @Operation(summary = "이메일 인증", description = "ENUM 값 확인이후 userType 보내주시면됩니다.")
    @ApiResponse(code = 200, message = "SUCCESS")
    public SingleResponse<String> confirmEmail(@RequestBody RequestMailDto req) throws MessagingException {
        return resp.getSingleResponse(mailService.sendSms(req));
    }

}