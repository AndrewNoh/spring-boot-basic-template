package andrew.backend.app.domain.admin.blame.model.dto;

import andrew.backend.app.domain.admin.blame.model.enums.BlameType;
import andrew.backend.app.domain.admin.blame.model.enums.TargetType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestBlameDto {

    @Schema(description = "신고할 ID")
    private Long targetId;

    @Schema(description = "신고할 유형")
    private TargetType targetType;

    @Schema(description = "신고 종류")
    private BlameType blameType;

    @Schema(description = "ETC 일 시 내용")
    private String blameBody;

}
