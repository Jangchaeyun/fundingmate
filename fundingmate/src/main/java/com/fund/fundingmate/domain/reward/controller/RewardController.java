package com.fund.fundingmate.domain.reward.controller;

import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.reward.dto.RewardCommentDTO;
import com.fund.fundingmate.domain.reward.dto.RewardDTO;
import com.fund.fundingmate.domain.reward.dto.RewardReplyDTO;
import com.fund.fundingmate.domain.reward.dto.RewardTypeDTO;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.reward.entity.RewardType;
import com.fund.fundingmate.domain.reward.service.RewardCommentService;
import com.fund.fundingmate.domain.reward.service.RewardService;
import com.fund.fundingmate.global.file.Repository.FileRepository;
import com.fund.fundingmate.global.file.Service.FileService;
import com.fund.fundingmate.global.file.dto.FileDTO;
import com.fund.fundingmate.global.file.entity.File;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @Autowired
    private FileService fileService;

    @Autowired
    private HttpSession session;

    @Autowired
    private RewardCommentService rewardCommentService;

    private RewardCommentDTO rewardCommentDTO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileRepository fileRepository;

    @PostMapping("/makeReward")
    public ResponseEntity<Long> createReward(@ModelAttribute RewardDTO rewardDTO,
                                                  @RequestParam("userId") Long userId,
                                                  @RequestParam("cards") String cards,
                                                  @RequestParam(value="rewardRepImg", required = false) MultipartFile repFile,
                                                  @RequestParam(value="rewardContentImg", required = false) MultipartFile[] contentFiles,
                                                  @RequestParam(value="rewardBusinessLicenseImg", required = false )  MultipartFile businessFile,
                                                  @RequestParam(value="rewardBankAccountCopyImg", required = false) MultipartFile bankFile) {
        try {
            System.out.println(userId);
            System.out.println(contentFiles.length);
            Long savedRewardId = rewardService.createReward(rewardDTO, userId, cards, repFile, contentFiles, businessFile, bankFile);
            return new ResponseEntity<>(savedRewardId, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//    @PostMapping("/makeReward1")
//    public ResponseEntity<RewardDTO> createReward(@RequestBody RewardDTO rewardDTO, @RequestParam("userId") Long userId,
//                                                  @RequestParam("rewardContentImages") List<MultipartFile> rewardContentImages) {
//        try {
//            if (rewardDTO != null) {
//                // Save reward content images
//                List<File> rewardContentImgEntities = saveMultipleFiles(rewardContentImages);
//
//                // Set the rewardContentImgSavedName in the rewardDTO
//                List<FileDTO> rewardContentImgSavedNames = rewardContentImgEntities.stream()
//                        .map(fileEntity -> modelMapper.map(fileEntity, FileDTO.class))
//                        .collect(Collectors.toList());
//                rewardDTO.setRewardContentImgSavedName(rewardContentImgSavedNames);
//
//                // Save the reward
//                Long savedRewardId = rewardService.createReward(rewardDTO, userId);
//                RewardDTO createdReward = rewardService.getRewardById(savedRewardId);
//                return ResponseEntity.status(HttpStatus.CREATED).body(createdReward);
//            } else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//            }
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

    private List<File> saveMultipleFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<File> savedFiles = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            File savedFile = fileService.saveFile(null, multipartFile);
            fileRepository.save(savedFile);
            savedFiles.add(savedFile);
        }
        return savedFiles;
    }


    @GetMapping("/reward-detail/story/{rewardId}")
    public ResponseEntity<RewardDTO> rewardDetailStory(@PathVariable Long rewardId) {
        System.out.println("rewardId:"+rewardId);

        try {
            String userId = (String) session.getAttribute("userId");
            System.out.println("rewardDetail: " + userId);
            RewardDTO rewardDetail = rewardService.getRewardById(rewardId);
            return ResponseEntity.ok(rewardDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/reward-detail/guide/{rewardId}")
    public ResponseEntity<RewardDTO> rewardDetailGuide(@PathVariable Long rewardId, HttpSession session) {
        try {
            String userId = (String) session.getAttribute("userId");
            System.out.println("rewardDetail: " + userId);
            RewardDTO rewardDetail = rewardService.getRewardById(rewardId);
            return ResponseEntity.ok(rewardDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/reward-detail/contact/{rewardId}")
    public ResponseEntity<List<RewardCommentDTO>> rewardDetailContact(@PathVariable("rewardId") Long rewardId) {
        List<RewardCommentDTO> rewardComments = rewardCommentService.getRewardCommentsByRewardId(rewardId);

        if (rewardComments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rewardComments);
    }

    @PostMapping("/reward-detail/contact/{rewardId}")
    public ResponseEntity<List<RewardCommentDTO>> rewardDetailContactWrite(@PathVariable("rewardId") Long rewardId, @RequestBody RewardCommentDTO requestBody) {
        try {
            rewardCommentService.insertRewardComment(requestBody);

            List<RewardCommentDTO> rewardComments = rewardCommentService.getRewardCommentsByRewardId(rewardId);
            return new ResponseEntity<>(rewardComments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/reward-detail/contact/comment/{commentId}")
    public ResponseEntity<List<RewardCommentDTO>> deleteRewardComment(@PathVariable("commentId") Long commentId) {
        try {
            rewardCommentService.deleteRewardComment(commentId);

            List<RewardCommentDTO> rewardComments = rewardCommentService.getRewardCommentsByRewardId(commentId);
            return new ResponseEntity<>(rewardComments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static final String UPLOAD_DIRECTORY = "D:/웹 애플리케이션 Full-Stack 과정/fundingmate/imgUpload";

    @GetMapping("/img/{fileOriginalName}")
    public void imageView(@PathVariable String fileOriginalName, HttpServletResponse response) {
        try {
            String filePath = UPLOAD_DIRECTORY + "/" + fileOriginalName;

            Path imagePath = Paths.get(filePath);
            byte[] fileBytes = Files.readAllBytes(imagePath);

            response.setContentType("image/*");

            response.getOutputStream().write(fileBytes);
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/upload-image")
    public void uploadImage(@RequestPart MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String filePath = UPLOAD_DIRECTORY + "/" + fileName;

            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/reward-detail/contact/comment/reply")
    public ResponseEntity<List<RewardCommentDTO>> rewardDetailContactReply(@RequestBody RewardReplyDTO requestBody) {
        try {
            rewardCommentService.insertRewardCommentReply(requestBody);

            List<RewardCommentDTO> rewardComments = rewardCommentService.getRewardCommentsByRewardId(requestBody.getRewardId());
            return new ResponseEntity<>(rewardComments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reward-detail/contact/comment/reply/{commentId}") // 경로 오타 수정
    public ResponseEntity<List<RewardReplyDTO>> rewardDetailCommentReplies(@PathVariable("commentId") Long commentId) {
        try {
            List<RewardReplyDTO> replies = rewardCommentService.getRewardCommentReplies(commentId);
            return ResponseEntity.ok(replies);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/reward-detail/find/rewarding")
    public ResponseEntity<List<RewardDTO>> rewardingFind() {
        try {
            List<RewardDTO> rewardingRewards = rewardService.getRewardWithProjDateStartEndBetween();
            return ResponseEntity.ok(rewardingRewards);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/reward/find/rewarding/more")
    public ResponseEntity<List<RewardDTO>> rewardingFindmore(@RequestParam int startIndex, @RequestParam int endIndex) {
        try {
            List<RewardDTO> rewardingRewards = rewardService.getRewardWithProjDateStartEndBetween();
            int totalRewards = rewardingRewards.size();
            if (startIndex >= totalRewards) {
                return ResponseEntity.ok().body(null);
            } else {
                endIndex = Math.min(endIndex, totalRewards);
                List<RewardDTO> nextRewards = rewardingRewards.subList(startIndex, endIndex);
                return ResponseEntity.ok(nextRewards);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/reward/find/prereward")
    public ResponseEntity<List<RewardDTO>> prerewardFind() {
        try {
            List<RewardDTO> prerewardRewards = rewardService.getRewardWithProjDateEndBeforeToday();
            return ResponseEntity.ok(prerewardRewards);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/reward/find/finishreward/more")
    public ResponseEntity<List<RewardDTO>> finishrewardFindmore(@RequestParam int startIndex, @RequestParam int endIndex) {
        try {
            List<RewardDTO> rewardingRewards = rewardService.getRewardWithProjDateEndBefore();
            int totalRewards = rewardingRewards.size();
            if (startIndex >= totalRewards) {
                return ResponseEntity.ok().body(null);
            } else {
                endIndex = Math.min(endIndex, totalRewards);
                List<RewardDTO> nextRewards = rewardingRewards.subList(startIndex, endIndex);
                return ResponseEntity.ok(nextRewards);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/reward/rewardcheckout/check/{rewardId}")
    public ResponseEntity<List<RewardTypeDTO>> getRewardTypesByRewardId(@PathVariable("rewardId") Long rewardId) {
        try {
            List<RewardTypeDTO> rewardTypes = rewardService.getRewardTypesByRewardId(rewardId);
            return ResponseEntity.ok(rewardTypes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/reward/rewardcheckout/checktype/{rewardId}/{rewardTypeId}")
    public ResponseEntity<RewardTypeDTO> getRewardTypeId(@PathVariable("rewardId") Long rewardId, @PathVariable("rewardTypeId") Long rewardTypeId) {
        try {
            RewardTypeDTO rewardType = rewardService.getRewardTypeById(rewardTypeId);
            if (rewardType != null) {
                return ResponseEntity.ok(rewardType);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            // IllegalArgumentException is thrown when reward type is not found
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // For any other unexpected exceptions
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}