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
//        String fileSavedName = genetateUniqueFileName(multipartFile.getOriginalFilename());
//        String folderPath = "E:/웹 애플리케이션 Full-Stack 과정/fundingmate/imgUpload/";
//        java.io.File folder = new java.io.File(folderPath);
//        if (!folder.exists()) {
//            folder.mkdirs();
//        }
//
//        String filePath = folderPath + fileSavedName;
//
//        Path targetLocation = Path.of(filePath);
//        Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//        File file = new File();
//        file.setFileSavedName(fileSavedName);
//        file.setFileOriginalName(multipartFile.getOriginalFilename());
//        file.setFileSize(formatFileSize(multipartFile.getSize()));
//        file.setFilePath(filePath);
//        file.setFileRegistrationDate(new Date());
//
//        return fileRepository.save(file);
//    }
//
//    public void writeFileToResponse(File file, HttpServletResponse response) throws IOException {
//        Path filePath = Path.of(file.getFilePath());
//        Files.copy(filePath, response.getOutputStream());
    }


    private String genetateUniqueFileName(String originalFilename) {
        long timestamp = System.currentTimeMillis();
        return timestamp + "-" + originalFilename;
    }

    private String formatFileSize(long size) {
        return size + " bytes";
    }



    public List<FileDTO> listSaveFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<FileDTO> savedFiles = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String originalFileName = multipartFile.getOriginalFilename();
            String fileSavedName = listGenetateUniqueFileName(originalFileName);
            String filePath = UPLOAD_DIRECTORY + "/" + fileSavedName;

            java.io.File file = new java.io.File(filePath);
            file.getParentFile().mkdirs();

            Path destination = file.toPath();

            // 파일 저장 로직
            // multipartFile을 저장하고 필요한 파일 정보를 저장된 File 객체로 설정

            File savedFile = new File();
            savedFile.setFileName(fileSavedName);
            savedFile.setFileRegistrationDate(new Date());

            // File 객체를 FileDTO로 변환하여 리스트에 추가
            FileDTO savedFileDTO = new FileDTO();
            savedFileDTO.setFileId(savedFile.getFileId());
            savedFileDTO.setFileName(savedFile.getFileName());
            savedFileDTO.setFileRegistrationDate(savedFile.getFileRegistrationDate());

            savedFiles.add(savedFileDTO);
        }
        return savedFiles;
    }


    private String listGenetateUniqueFileName(String originalFilename) {
        long timestamp = System.currentTimeMillis();
        return timestamp + "-" + originalFilename;
    }

}
