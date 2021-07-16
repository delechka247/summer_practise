package ru.itis.javalab.videomicroservice.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.videomicroservice.dto.FileInfoDto;
import ru.itis.javalab.videomicroservice.models.FileInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface FileStorageService {
    String saveFile(MultipartFile file);
    void writeFileToResponse(String fileName, HttpServletResponse response);
    List<FileInfoDto> getAllVideosByTopic(Long topicId);
    List<FileInfoDto> getAllSynopsesByTopic(Long topicId);
}