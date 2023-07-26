package com.fund.fundingmate.global.file.Service;

import com.fund.fundingmate.global.file.Repository.FileRepository;
import com.fund.fundingmate.global.file.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileService {
    private final FileRepository fileRepository;
  
    public static final String UPLOAD_DIRECTORY = "D:/웹 애플리케이션 Full-Stack 과정/fundingmate/imgUpload";

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File saveFile(File existingFile, MultipartFile multipartFile) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();
        String filsSavedName = genetateUniqueFileName(originalFileName);
        String filePath = UPLOAD_DIRECTORY + "/" + filsSavedName;

        java.io.File file = new java.io.File(filePath);
        file.getParentFile().mkdirs();

        Path destination = file.toPath();
        Files.copy(multipartFile.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        File savedFile = new File();
        savedFile.setFileName(filsSavedName);
        savedFile.setFileRegistrationDate(new Date());

        return savedFile;
    }



    private List<File> saveMultipleFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<File> savedFiles = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            File savedFile = saveFile(null, multipartFile);
            fileRepository.save(savedFile);
            savedFiles.add(savedFile);
        }
        return savedFiles;
    }

    private String genetateUniqueFileName(String originalFilename) {
        long timestamp = System.currentTimeMillis();
        return timestamp + "-" + originalFilename;
    }

    private String formatFileSize(long size) {
        return size + " bytes";
    }

}
