package ru.itis.javalab.videomicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.videomicroservice.models.FileInfo;
import ru.itis.javalab.videomicroservice.models.Topic;
import ru.itis.javalab.videomicroservice.util.FileType;

import java.util.List;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findByAndStorageName(String fileName);
    List<FileInfo> findAllByTopicAndFileType(Topic topic, FileType fileType);
}
