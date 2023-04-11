package andrew.backend.app.domain.admin.onboarding.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "RequestWriteOnboardingDto", description = "온보딩 작성 DTO")
public class RequestWriteOnboardingDto {
    @ApiModelProperty(value = "어드민 ID")
    Long adminId;
    @ApiModelProperty(value = "국가 ID")
    Long codeId;
}
