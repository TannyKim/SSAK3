package org.ssak3.api.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssak3.api.example.service.AmazonS3Service;

import java.util.List;

@Tag(name="S3 REST API", description = "S3 REST API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/upload")
public class AmazonS3Controller {

    private final AmazonS3Service amazonS3Service;

    @Operation(summary = "S3 입니다.", description = "이미지 업로드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 업로드 성공"),
            @ApiResponse(responseCode = "404", description = "이미지 업로드 실패")
    })
    @PostMapping("/uploads")
    public ResponseEntity<Object> uploadFiles(
            @RequestParam(value = "fileType") String fileType,
            @RequestPart(value = "files") List<MultipartFile> multipartFiles) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(amazonS3Service.uploadFiles(fileType, multipartFiles));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteFile(
            @RequestParam(value = "uploadFilePath") String uploadFilePath,
            @RequestParam(value = "uuidFileName") String uuidFileName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(amazonS3Service.deleteFile(uploadFilePath, uuidFileName));
    }
}
