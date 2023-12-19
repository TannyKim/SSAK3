package org.ssak3.api.example.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.ssak3.api.example.dto.S3FileDto;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service
@RequiredArgsConstructor
public class AmazonS3Service{

    @Value("cloud.aws.s3.bucketName")
    private String bucketName;

    private final AmazonS3Client amazonS3Client;
//
//    /**
//     * S3로 파일 업로드
//     */
    public List<S3FileDto> uploadFiles(String fileType, List<MultipartFile> multipartFiles) {

        List<S3FileDto> s3files = new ArrayList<>();

        String uploadFilePath = fileType + "/" + getFolderName();

        for (MultipartFile multipartFile : multipartFiles) {

            String originalFileName = multipartFile.getOriginalFilename();
            String uploadFileName = getUuidFileName(originalFileName);
            String uploadFileUrl = "";

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(multipartFile.getSize());
            objectMetadata.setContentType(multipartFile.getContentType());

            try (InputStream inputStream = multipartFile.getInputStream()) {

                String keyName = uploadFilePath + "/" + uploadFileName; // ex) 구분/년/월/일/파일.확장자

                // S3에 폴더 및 파일 업로드
                amazonS3Client.putObject(
                        new PutObjectRequest(bucketName, keyName, inputStream, objectMetadata));

                // TODO : 외부에 공개하는 파일인 경우 Public Read 권한을 추가, ACL 확인
        /*amazonS3Client.putObject(
            new PutObjectRequest(bucket, s3Key, inputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));*/

                // S3에 업로드한 폴더 및 파일 URL
                uploadFileUrl = amazonS3Client.getUrl(bucketName, keyName).toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            s3files.add(
                    S3FileDto.builder()
                            .originalFileName(originalFileName)
                            .uploadFileName(uploadFileName)
                            .uploadFilePath(uploadFilePath)
                            .uploadFileUrl(uploadFileUrl)
                            .build());
        }

        return s3files;
    }
//
//
    public String deleteFile(String uploadFilePath, String uuidFileName) {

        String result = "success";

        try {
            String keyName = uploadFilePath + "/" + uuidFileName; // ex) 구분/년/월/일/파일.확장자
            boolean isObjectExist = amazonS3Client.doesObjectExist(bucketName, keyName);
            if (isObjectExist) {
                amazonS3Client.deleteObject(bucketName, keyName);
            } else {
                result = "file not found";
            }
        } catch (Exception e) {
        }

        return result;
    }
//
    public String getUuidFileName(String fileName) {
        String ext = fileName.substring(fileName.indexOf(".") + 1);
        return UUID.randomUUID().toString() + "." + ext;
    }
//
//    /**
//     * 년/월/일 폴더명 반환
//     */
    private String getFolderName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String str = sdf.format(date);
        return str.replace("-", "/");
    }
//
}

