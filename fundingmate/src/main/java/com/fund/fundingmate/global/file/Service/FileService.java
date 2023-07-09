package com.fund.fundingmate.global.file.Service;

import com.fund.fundingmate.global.file.Repository.FileRepository;
import com.fund.fundingmate.global.file.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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

    public File saveFile(File existingFile, MultipartFile multipartFile) throws IOException {
        String fileSavedName = genetateUniqueFileName(multipartFile.getOriginalFilename());
        String filePath = "D:/웹 애플리케이션 Full-Stack 과정/fundingmate/imgUpload/" + fileSavedName;

        Path targetLocation = Path.of(filePath);
        Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        File file = new File();
        file.setFileSavedName(fileSavedName);
        file.setFileOriginalName(multipartFile.getOriginalFilename());
        file.setFileSize(formatFileSize(multipartFile.getSize()));
        file.setFileRegistrationDate(new Date());

        return fileRepository.save(file);
    }

    public void writeFileToResponse(File file, HttpServletResponse response) throws IOException {
        Path filePath = Path.of(file.getFilePath());
        Files.copy(filePath, response.getOutputStream());
    }

    public File getFileByOriginalName(String fileOriginalName) {
        return fileRepository.findByFileOriginalName(fileOriginalName);
    }


    private String genetateUniqueFileName(String originalFilename) {
        long timestamp = System.currentTimeMillis();
        return timestamp + "-" + originalFilename;
    }

    private String formatFileSize(long size) {
        return size + " bytes";
    }
}
