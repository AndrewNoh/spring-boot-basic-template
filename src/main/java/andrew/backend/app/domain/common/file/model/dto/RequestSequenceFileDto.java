package andrew.backend.app.domain.common.file.model.dto;

import lombok.Data;

@Data
public class RequestSequenceFileDto {
    private Long fileId;
    private Integer sequence;
}
