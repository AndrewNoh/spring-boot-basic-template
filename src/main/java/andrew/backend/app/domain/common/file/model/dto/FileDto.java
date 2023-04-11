package andrew.backend.app.domain.common.file.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FileDto {

    @Schema(description = "파일 ID")
    private Long fileId;
    @Schema(description = "파일주소")
    private String s3Key;
    @Schema(description = "파일명")
    private String fileName;
}
