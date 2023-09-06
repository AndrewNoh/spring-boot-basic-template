package andrew.backend.app.global.util;

import andrew.backend.app.domain.common.file.model.dto.FileDto;
import andrew.backend.app.domain.common.file.model.entity.FileEntity;
import andrew.backend.app.domain.common.file.model.enums.FileType;
import andrew.backend.app.domain.common.file.model.repository.FileRepo;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@RequiredArgsConstructor
@Component
@Transactional
public class S3Utils {

    private final AmazonS3Client s3Client;
    private final FileRepo fileRepo;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private final Map<String, String> folderNamesByExtension = Map.of(
            "jpg", "image",
            "jpeg", "image",
            "png", "image",
            "gif", "image",
            "mp4", "video",
            "avi", "video",
            "mov", "video"
    );

    public FileDto saveFile(MultipartFile multipartFile, FileType fileType) throws IOException {
        FileEntity fileEntity = uploadFileToS3AndSaveEntity(multipartFile, fileType);
        return createFileDto(fileEntity, null);
    }

    public FileDto saveFile(MultipartFile videoFile, MultipartFile thumbnailFile, FileType fileType) throws IOException {
        FileEntity videoEntity = uploadFileToS3AndSaveEntity(videoFile, fileType);
        FileEntity thumbnailEntity = uploadFileToS3AndSaveEntity(thumbnailFile, fileType);

        videoEntity.getChildren().add(thumbnailEntity);
        thumbnailEntity.setParent(videoEntity);

        fileRepo.save(thumbnailEntity);

        List<FileDto> children = mapFileEntityListToDtoList(videoEntity.getChildren());
        return createFileDto(videoEntity, children);
    }

    public FileEntity uploadFileToS3AndSaveEntity(MultipartFile multipartFile, FileType fileType) throws IOException {

        String staticOrUpload = fileType.equals(FileType.ADMIN) ? "static/" : "upload/";
        // 업로드할 파일의 원본 파일 이름 가져오기
        String originalFileName = multipartFile.getOriginalFilename();

        if (originalFileName == null) throw new CustomException(ErrorCode.NOT_VALID_METHOD_ARGUMENT);

        // 파일 확장자 가져오기
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        // 업로드할 폴더 이름 설정 (파일 확장자에 따라 폴더를 다르게 지정)
        String folderName = folderNamesByExtension.getOrDefault(fileExtension.toLowerCase(), "other");
        // 저장할 파일 이름 생성 (UUID와 파일 확장자를 조합)
        String saveName = UUID.randomUUID() + "." + fileExtension;
        // S3에 저장될 파일 경로 생성
        String s3FileName = staticOrUpload + folderName + "/" + saveName;

        long maxFileSize = (folderName.equals("video")) ? 31457280 : 10485760;
        if (multipartFile.getSize() > maxFileSize) {
            throw new CustomException(ErrorCode.FILE_TOO_LARGE);
        }

        // 파일 메타데이터 생성
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(multipartFile.getSize());

        // S3에 파일 업로드를 위한 PutObjectRequest 생성
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3FileName, multipartFile.getInputStream(), metadata).withCannedAcl(CannedAccessControlList.PublicRead);

        // 사전 서명된 URL 생성을 위한 GeneratePresignedUrlRequest 생성
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(putObjectRequest.getBucketName(), putObjectRequest.getKey()).withMethod(HttpMethod.PUT).withExpiration(getExpiration());

        // 사전 서명된 URL 생성
        URL preSignedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        // 사전 서명된 URL을 사용하여 파일 업로드
        uploadFileToPresignedUrl(preSignedUrl, multipartFile);

        // 파일 엔티티 저장
        return saveFileEntity(originalFileName, s3FileName, saveName, fileExtension, multipartFile.getSize());
    }

    private FileEntity saveFileEntity(String originalFileName, String s3FileName, String saveName, String fileExtension, long size) {
        FileEntity file = new FileEntity();
        file.setFilename(originalFileName);
        file.setSize(size);
        file.setSaveName(saveName);
        file.setS3name(s3FileName);
        file.setMimeType(fileExtension);
        file.setIsDeleted(false);
        return fileRepo.save(file);
    }

    private void uploadFileToPresignedUrl(URL presignedUrl, MultipartFile multipartFile) throws IOException {
        // 사전 서명된 URL로부터 HttpURLConnection 객체 생성
        HttpURLConnection connection = (HttpURLConnection) presignedUrl.openConnection();
        // 출력 허용 설정
        connection.setDoOutput(true);
        // HTTP 요청 방식 설정 (PUT 요청)
        connection.setRequestMethod(HttpMethod.PUT.name());
        // 요청에 CONTENT_TYPE 헤더 설정 (파일의 Content-Type 설정)
        connection.setRequestProperty(HttpHeaders.CONTENT_TYPE, multipartFile.getContentType());

        // HttpURLConnection의 출력 스트림을 사용하여 파일 업로드
        try (OutputStream outputStream = connection.getOutputStream()) {
            // MultipartFile의 입력 스트림을 출력 스트림으로 복사
            IOUtils.copy(multipartFile.getInputStream(), outputStream);
        }

        // 서버 응답 상태 코드 확인
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpStatus.OK.value()) {
            throw new RuntimeException("Failed to upload file. Response code: " + responseCode);
        }
    }

    private Date getExpiration() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 15);
        return calendar.getTime();
    }

    public String generatePreSignedUrl(String key) {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60; // 1 hour
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key).withMethod(HttpMethod.GET).withExpiration(expiration);

        return s3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    public String noLimitGeneratePreSignedUrl(String key) {
        Date expirationDate = new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000));
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key).withMethod(HttpMethod.GET).withExpiration(expirationDate);
        return s3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }


    public void deleteFilesFromS3(List<Long> fileIds) {
        // Retrieve the file objects from the file repository using the file IDs
        List<FileEntity> files = fileRepo.findAllById(fileIds);

        // Iterate over the file objects and delete the corresponding files from S3
        for (FileEntity file : files) {
            // Extract the S3 storage name from the file object
            String s3FileName = file.getS3name();
            // Delete the file from S3
            s3Client.deleteObject(bucketName, s3FileName);
        }
        fileRepo.deleteAll(files);
    }

    private List<FileDto> mapFileEntityListToDtoList(List<FileEntity> fileEntities) {
        return fileEntities.stream()
                .map(file -> new FileDto(file.getFileId(), generatePreSignedUrl(file.getS3name()), file.getFilename(), file.getSequence()))
                .toList();
    }

    private FileDto createFileDto(FileEntity fileEntity, List<FileDto> children) {
        return new FileDto(
                fileEntity.getFileId(),
                generatePreSignedUrl(fileEntity.getS3name()),
                fileEntity.getFilename(),
                fileEntity.getSequence(),
                children
        );
    }
}

