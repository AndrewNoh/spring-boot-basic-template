package andrew.backend.app.domain.common.file.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import andrew.backend.app.domain.common.file.model.entity.FileEntity;
import andrew.backend.app.global.util.S3Utils;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    @Schema(description = "파일 ID")
    private Long fileId;
    @Schema(description = "파일주소")
    private String s3Key;
    @Schema(description = "파일명")
    private String filename;
    @Schema(description = "순서")
    private Integer sequence;
    @Schema(description = "동영상일 시에만 썸네일 제공")
    private List<FileDto> childrenThumbnail;

    public FileDto(FileEntity fileEntity, S3Utils s3Utils) {
        s3Key = s3Utils.generatePreSignedUrl(fileEntity.getS3name());
        filename = fileEntity.getFilename();
        fileId = fileEntity.getFileId();
        sequence = fileEntity.getSequence();
        if (!fileEntity.getChildren().isEmpty()) {
            childrenThumbnail = fileEntity.getChildren().stream().map(file -> new FileDto(file, s3Utils)).toList();
        }
    }

    public FileDto(Long fileId, String s3Key, String filename, Integer sequence) {
        this.fileId = fileId;
        this.s3Key = s3Key;
        this.filename = filename;
        this.sequence = sequence;
    }
}
