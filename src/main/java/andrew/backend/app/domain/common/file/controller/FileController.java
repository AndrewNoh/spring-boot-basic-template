package andrew.backend.app.domain.common.file.controller;

import andrew.backend.app.domain.common.file.model.dto.FileDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import andrew.backend.app.domain.common.file.model.dto.FileUploadDto;
import andrew.backend.app.domain.common.file.model.dto.RequestFileIdDto;
import andrew.backend.app.domain.common.file.model.enums.FileType;
import andrew.backend.app.global.exception.CustomException;
import andrew.backend.app.global.exception.ErrorCode;
import andrew.backend.app.global.response.CommonResponse;
import andrew.backend.app.global.response.ListResponse;
import andrew.backend.app.global.response.ResponseService;
import andrew.backend.app.global.response.SingleResponse;
import andrew.backend.app.global.util.S3Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "File", description = "파일 API")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "PERM_RESIDENT")
public class FileController {

    private final S3Utils s3Utils;
    private final ResponseService resp;

    @PostMapping(value = "client/save", consumes = {
            "multipart/form-data"
    })
    @Tag(name = "File")
    @Operation(summary = "파일 저장", description = "파일이 존재 할시")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "413", description = "File size is too large."),
            @ApiResponse(responseCode = "409", description = "Update Fail")})
    public ListResponse<FileDto> saveFile(@RequestParam("files") List<MultipartFile> files) throws IOException {
        List<FileDto> fileId = new ArrayList<>();
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files)
                fileId.add(s3Utils.saveFile(file, FileType.USER));
        }
        return resp.getListResponse(fileId);
    }

    @PostMapping(value = "client/save/media", consumes = "multipart/form-data")
    @Tag(name = "File")
    @Operation(summary = "Save Media file", description = "If file exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "413", description = "File size is too large."),
            @ApiResponse(responseCode = "409", description = "Update Fail")})
    public SingleResponse<FileDto> saveFile(@ModelAttribute @Valid FileUploadDto fileUploadDto) throws IOException {
        MultipartFile videoFile = fileUploadDto.getVideoFile();
        MultipartFile thumbnailFile = fileUploadDto.getThumbnailFile();
        return resp.getSingleResponse(s3Utils.saveFile(videoFile, thumbnailFile, FileType.USER));
    }

    @DeleteMapping("client/delete")
    @Tag(name = "File")
    @Operation(summary = "파일 삭제", description = "업로드 실패시에만 사용 이 외에 모든 상황에서는 soft delete처리.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "SUCCESS"), @ApiResponse(responseCode = "409", description = "파일 삭제 실패")})
    public CommonResponse deleteFile(@RequestBody RequestFileIdDto req) {
        try {
            s3Utils.deleteFilesFromS3(req.getFileIds());
            return resp.defaultSuccessResponse();
        } catch (Exception e) {
            throw new CustomException(ErrorCode.UPDATE_FAILED);
        }
    }


    @PostMapping(value = "admin/save", consumes = {
            "multipart/form-data"
    })
    @Tag(name = "File")
    @Operation(summary = "파일 저장", description = "파일이 존재 할시")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Invalid file extension."),
            @ApiResponse(responseCode = "413", description = "File size is too large."),
            @ApiResponse(responseCode = "409", description = "Update Fail")})
    public ListResponse<FileDto> saveFileAdmin(
            @RequestPart List<MultipartFile> files,
            @RequestParam(required = false) Long sizeLimit,
            @RequestParam(required = false) List<String> allowedExtensions) throws IOException {

        List<FileDto> fileId = new ArrayList<>();
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if(sizeLimit != null && allowedExtensions !=null){
                    if (file.getSize() > sizeLimit) {
                        throw new CustomException(ErrorCode.FILE_TOO_LARGE);
                    }
                    String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
                    if (!allowedExtensions.contains(fileExtension)) {
                        throw new CustomException(ErrorCode.INVALID_FILE_EXTENSION);
                    }
                }

                fileId.add(s3Utils.saveFile(file, FileType.ADMIN));
            }
        }
        return resp.getListResponse(fileId);
    }

    @DeleteMapping("admin/delete/{file-ids}")
    @Tag(name = "File")
    @Operation(summary = "파일 삭제", description = "업로드 실패시에만 사용 이 외에 모든 상황에서는 soft delete처리.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "SUCCESS"), @ApiResponse(responseCode = "409", description = "파일 삭제 실패")})
    public CommonResponse deleteFileAdmin(@PathVariable("file-ids") List<Long> fileIds) {
        try {
            s3Utils.deleteFilesFromS3(fileIds);
            return resp.defaultSuccessResponse();
        } catch (Exception e) {
            throw new CustomException(ErrorCode.UPDATE_FAILED);
        }
    }
}
