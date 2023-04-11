package andrew.backend.app.domain.common.file.controller;

import andrew.backend.app.global.response.ListResponse;
import andrew.backend.app.global.response.ResponseService;
import andrew.backend.app.global.util.S3Utils;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "file", description = "파일 API")
@RestController
@RequiredArgsConstructor
public class FileController {

    private final S3Utils s3Utils;
    private final ResponseService resp;

    @PostMapping("save")
    @Tag(name = "file")
    @Operation(summary = "파일 저장", description = "파일이 존재 할시")
    @ApiResponse(code = 200, message = "SUCCESS")
    public ListResponse<Long> saveFile(@RequestPart(required = false) List<MultipartFile> files) throws IOException {
        List<Long> fileId = new ArrayList<>();
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files)
                fileId.add(s3Utils.uploadFileToS3AndSaveEntity(file));
        }
        return resp.getListResponse(fileId);
    }
}
