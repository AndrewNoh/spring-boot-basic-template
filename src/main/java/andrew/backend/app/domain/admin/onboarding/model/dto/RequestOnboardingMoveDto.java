package andrew.backend.app.domain.admin.onboarding.model.dto;

import andrew.backend.app.domain.admin.onboarding.model.UpAndDown;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestOnboardingMoveDto {
    @ApiModelProperty(value = "이동할 온보딩의 ID")
    private Long onId;
    @ApiModelProperty(value = "이동할 온보딩의 ID")
    private Long codeId;
    @ApiModelProperty(value = "온보딩 이동 방향 ('up': 위로, 'down': 아래로)")
    private UpAndDown direction;
}
