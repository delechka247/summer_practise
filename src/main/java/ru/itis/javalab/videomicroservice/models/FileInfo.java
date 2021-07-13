package ru.itis.javalab.videomicroservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.videomicroservice.util.FileType;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String storageName;
    private String originalName;
    private String type;
    private Long size;
    private String url;
    @Enumerated(EnumType.STRING)
    private FileType fileType;
}
