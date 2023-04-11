package andrew.backend.app.global.util;

import andrew.backend.app.domain.common.file.model.entity.FileEntity;
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
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@RequiredArgsConstructor
@Component
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

    public Long uploadFileToS3AndSaveEntity(MultipartFile multipartFile) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String folderName = "upload/" + folderNamesByExtension.getOrDefault(fileExtension.toLowerCase(), "other");
        String saveName = UUID.randomUUID() + "." + fileExtension;
        String s3FileName = folderName + "/" + saveName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(multipartFile.getSize());

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3FileName, multipartFile.getInputStream(), metadata).withCannedAcl(CannedAccessControlList.PublicRead);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(putObjectRequest.getBucketName(), putObjectRequest.getKey()).withMethod(HttpMethod.PUT).withExpiration(getExpiration());

        URL preSignedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        uploadFileToPresignedUrl(preSignedUrl, multipartFile);

        FileEntity file = saveFileEntity(originalFileName, s3FileName, saveName, fileExtension, multipartFile.getSize());
        return file.getFileId();
    }

    private FileEntity saveFileEntity(String originalFileName, String s3FileName,String saveName, String fileExtension, long size) {
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
        HttpURLConnection connection = (HttpURLConnection) presignedUrl.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod(HttpMethod.PUT.name());
        connection.setRequestProperty(HttpHeaders.CONTENT_TYPE, multipartFile.getContentType());

        try (OutputStream outputStream = connection.getOutputStream()) {
            IOUtils.copy(multipartFile.getInputStream(), outputStream);
        }

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

    public String generatePresignedUrl(String key) {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60; // 1 hour
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key).withMethod(HttpMethod.GET).withExpiration(expiration);

        return s3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }
}

