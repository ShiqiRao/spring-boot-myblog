package com.example.myblog.controller;

import com.example.myblog.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {

    private final FileStorageService fileStorageService;

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return String.format("/file/%s", fileStorageService.uploadFile(file));
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        Resource ret;
        try {
            ret = fileStorageService.loadFile(fileName);
        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found.");
        }
        return downloadFile(ret, request);
    }

    private ResponseEntity<Resource> downloadFile(Resource resource, HttpServletRequest request) {
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            log.error("Could not determine file type.");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + resource.getFilename())
                .body(resource);
    }

}
