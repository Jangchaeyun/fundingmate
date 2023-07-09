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
import java.util.Date;

@Service
public class FileService {
    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File saveFile(MultipartFile multipartFile) throws IOException {
        String fileSavedName = genetateUniqueFileName(multipartFile.getOriginalFilename());
        String filePath = "D:/웹 애플리케이션 Full-Stack 과정/fundingmate/fundingmate/src/main/resources/imgUpload/" + fileSavedName;

        Path targertLocation = Path.of(filePath);
        Files.copy(multipartFile.getInputStream(), targertLocation, StandardCopyOption.REPLACE_EXISTING);

        File file = new File();
        file.setFileSavedName(fileSavedName);
        file.setFileOriginalName(multipartFile.getOriginalFilename());
        file.setFilePath(filePath);
        file.setFileSize(formatFileSize(multipartFile.getSize()));
        file.setFileRegistrationDate(new Date());

        return fileRepository.save(file);
    }

    private String genetateUniqueFileName(String originalFilename) {
        long timestamp = System.currentTimeMillis();
        return timestamp + "-" + originalFilename;
    }

    private String formatFileSize(long size) {
        return size + " bytes";
    }
}
