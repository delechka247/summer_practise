package ru.itis.javalab.videomicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.videomicroservice.models.FileInfo;
import ru.itis.javalab.videomicroservice.models.Topic;
import ru.itis.javalab.videomicroservice.models.User;
import ru.itis.javalab.videomicroservice.util.FileType;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileInfoDto {
    private String storageName;
    private String originalName;
    private Long size;
    private String url;

    public static FileInfoDto from(FileInfo fileInfo) {
        return FileInfoDto.builder()
                .storageName(fileInfo.getStorageName())
                .originalName(fileInfo.getOriginalName())
                .size(fileInfo.getSize())
                .url(fileInfo.getUrl())
                .build();
    }

    public static List<FileInfoDto> from(List<FileInfo> fileInfoList) {
        return fileInfoList.stream().map(FileInfoDto::from).collect(Collectors.toList());
    }
}
