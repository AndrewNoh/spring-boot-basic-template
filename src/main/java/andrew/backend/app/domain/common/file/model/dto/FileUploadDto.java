package andrew.backend.app.domain.common.file.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FileUploadDto {
    @NotNull
    private MultipartFile videoFile;
    @NotNull
    private MultipartFile thumbnailFile;
}
