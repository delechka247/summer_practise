package ru.itis.javalab.videomicroservice.services;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.videomicroservice.models.FileInfo;
import ru.itis.javalab.videomicroservice.repositories.FileInfoRepository;
import ru.itis.javalab.videomicroservice.util.FileType;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Value("${storage.path}")
    private String storagePath;

    @Override
    public String saveFile(MultipartFile uploadingFile) {
        String storageName = UUID.randomUUID() + "." + FilenameUtils.getExtension(uploadingFile.getOriginalFilename());
        FileType fileType;

        if(FilenameUtils.getExtension(uploadingFile.getOriginalFilename()).equals("mkv")
                || FilenameUtils.getExtension(uploadingFile.getOriginalFilename()).equals("avi")
                || FilenameUtils.getExtension(uploadingFile.getOriginalFilename()).equals("mov")
                || FilenameUtils.getExtension(uploadingFile.getOriginalFilename()).equals("mp4")) {
            fileType = FileType.VIDEO;
        } else if(FilenameUtils.getExtension(uploadingFile.getOriginalFilename()).equals("txt")
                || FilenameUtils.getExtension(uploadingFile.getOriginalFilename()).equals("doc")
                || FilenameUtils.getExtension(uploadingFile.getOriginalFilename()).equals("docx")
                || FilenameUtils.getExtension(uploadingFile.getOriginalFilename()).equals("pdf")) {
            fileType = FileType.SYNOPSES;
        }
        else {
            return "This file type is not supported. Try to download something else";
        }

        FileInfo fileInfo = FileInfo.builder()
                .type(uploadingFile.getContentType())
                .originalName(uploadingFile.getOriginalFilename())
                .size(uploadingFile.getSize())
                .storageName(storageName)
                .url(storagePath + "\\" + storageName)
                .fileType(fileType)
                .build();

        try {
            Files.copy(uploadingFile.getInputStream(), Paths.get(storagePath, storageName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        fileInfoRepository.save(fileInfo);
        return fileInfo.getStorageName();
    }

    @Override
    public void writeFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo fileInfo = fileInfoRepository.findByAndStorageName(fileName);
        response.setContentType(fileInfo.getType());
        try {
            IOUtils.copy(new FileInputStream(fileInfo.getUrl()), response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}