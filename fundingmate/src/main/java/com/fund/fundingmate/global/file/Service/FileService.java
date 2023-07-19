package com.fund.fundingmate.global.file.Service;

import com.fund.fundingmate.global.file.Repository.FileRepository;
import com.fund.fundingmate.global.file.dto.FileDTO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileService {
    private final FileRepository fileRepository;

    private static final String UPLOAD_DIRECTORY = "E:/웹 애플리케이션 Full-Stack 과정/fundingmate/imgUpload";

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

        File savedFile = new File();
        savedFile.setFileName(filsSavedName);
        savedFile.setFileRegistrationDate(new Date());

        return savedFile;
    }


    private String genetateUniqueFileName(String originalFilename) {
        long timestamp = System.currentTimeMillis();
        return timestamp + "-" + originalFilename;
    }

    private String formatFileSize(long size) {
        return size + " bytes";
    }

}
