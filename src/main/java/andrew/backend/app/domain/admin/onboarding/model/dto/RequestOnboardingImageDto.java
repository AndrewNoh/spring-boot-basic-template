package andrew.backend.app.domain.admin.onboarding.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "RequestOnboardingImageDto", description = "온보딩 이미지 수정")
public class RequestOnboardingImageDto extends OnboardingWriteDto {
    @ApiModelProperty(value = "온보딩 이미지 ID")
    Long fileId;
}
