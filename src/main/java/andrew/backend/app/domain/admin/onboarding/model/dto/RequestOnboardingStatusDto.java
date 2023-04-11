package andrew.backend.app.domain.admin.onboarding.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "RequestOnboardingStatusDto", description = "온보딩 상태 수정")
public class RequestOnboardingStatusDto extends OnboardingWriteDto {
    @ApiModelProperty(value = "온보딩 활성화 상태")
    Boolean activate;
}
