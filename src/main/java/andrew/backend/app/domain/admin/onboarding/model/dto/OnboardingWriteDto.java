package andrew.backend.app.domain.admin.onboarding.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class OnboardingWriteDto {
    @ApiModelProperty(value = "어드민 ID")
    Long adminId;
    @ApiModelProperty(value = "온보딩 ID")
    Long onId;
    @ApiModelProperty(value = "온보딩 타입")
    OnboardingType onType;

    public enum OnboardingType {
        TITLE,
        IMAGE,
        STATUS
    }
}
