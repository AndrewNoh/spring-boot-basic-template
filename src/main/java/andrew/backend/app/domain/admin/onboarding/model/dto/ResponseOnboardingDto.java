package andrew.backend.app.domain.admin.onboarding.model.dto;

import andrew.backend.app.domain.common.file.model.dto.FileDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Schema(name = "ResponseWriteOnboardingDto", description = "온보딩 DTO")
@Getter
@Setter
@NoArgsConstructor
public class ResponseOnboardingDto {
    @Schema(description = "온보딩 ID")
    private Long id;
    private Admin admin;
    private FileDto file;
    @Schema(description = "국가명")
    private String country;
    @Schema(description = "제목")
    private String title;
    @Schema(description = "현재상태")
    private Boolean status;
    @Schema(description = "게시글 순서")
    private Integer sequence;
    @Schema(description = "등록일")
    private Instant createdAt;
    @Schema(description = "수정일")
    private Instant updatedAt;
    @Data
    private static class Admin{
        @Schema(description = "관리자 ID")
        private Long adminId;
        @Schema(description = "관리자 이름")
        private String name;
    }
}
