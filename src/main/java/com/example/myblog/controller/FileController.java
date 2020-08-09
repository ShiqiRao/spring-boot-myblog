package com.example.myblog.controller;

import com.example.myblog.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {

    private final FileStorageService fileStorageService;

    @PostMapping("")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return String.format("/file/%s", fileStorageService.uploadFile(file));
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable String fileName, HttpServletRequest request) {
        //todo
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Under construction!");
    }

}
