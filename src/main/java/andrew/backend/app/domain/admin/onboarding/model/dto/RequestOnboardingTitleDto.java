package andrew.backend.app.domain.admin.onboarding.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "RequestOnboardingTitleDto", description = "어드민 제목 온보딩 수정")
public class RequestOnboardingTitleDto extends OnboardingWriteDto {
    @ApiModelProperty(value = "온보딩 제목")
    String title;
}
