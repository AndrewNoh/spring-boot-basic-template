package andrew.backend.app.domain.common.file.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class RequestFileIdDto {
    private List<Long> fileIds;
}
