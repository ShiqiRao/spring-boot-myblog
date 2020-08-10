package com.example.myblog.service;

import com.example.myblog.config.FileStorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService(FileStorageProperties fileStorageProperties) throws Exception {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    public String uploadFile(MultipartFile file) {
        //文件名
        String originalName = file.getOriginalFilename();
        //扩展名
        String extName = originalName == null || originalName.lastIndexOf(".") <= 0 ?
                null : originalName.substring(originalName.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extName;
        if (fileName.contains("..")) {
            throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
        }
        //根据文件名获得最终的Path对象
        Path target = fileStorageLocation.resolve(fileName);
        try {
            //将文件流拷贝至目标Path
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", e);
        }
        return fileName;
    }

    public Resource loadFile(String fileName) throws FileNotFoundException {
        Path filePath = fileStorageLocation.resolve(fileName).normalize();
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("file not found" + fileName);
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("file not found" + fileName);
        }
    }
}
