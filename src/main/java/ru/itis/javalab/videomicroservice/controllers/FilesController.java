package ru.itis.javalab.videomicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.videomicroservice.services.FileStorageService;

import javax.servlet.http.HttpServletResponse;

//TODO: security

@RestController
public class FilesController {

    @Autowired
    private FileStorageService fileStorageService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/files/upload")
    public ResponseEntity<String> fileUpload(@RequestHeader("TOKEN") String token,
                                             @RequestHeader("REFRESH-TOKEN") String refreshToken,
                                             @RequestParam("file") MultipartFile file) {

        System.out.println(file.getOriginalFilename());
        String filePath = fileStorageService.saveFile(file);

        return ResponseEntity.ok().body(filePath);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response,
                        @RequestHeader("TOKEN") String token,
                        @RequestHeader("REFRESH-TOKEN") String refreshToken) {
        fileStorageService.writeFileToResponse(fileName, response);
    }

}