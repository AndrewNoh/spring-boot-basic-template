package andrew.backend.app.domain.admin.onboarding.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ResponseOnboardingDto", description = "어드민 온보딩 정보")
public class RequestLanguageCodeDto {
    @ApiModelProperty(value = "언어코드 ko, vi, en", required = true)
    String languageCode;
}
