package com.fund.fundingmate.domain.banner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fund.fundingmate.domain.banner.entity.Banner;
import com.fund.fundingmate.domain.banner.repository.BannerRepository;
import com.fund.fundingmate.global.file.entity.File;
import com.querydsl.core.Tuple;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BannerController {
    @Autowired private BannerRepository bannerRepository;

    @GetMapping("/bannerList")
    public ResponseEntity<List> articleList() {
        try {
            System.out.println("성공");
//            Map<String,Object> map = new HashMap<>();
            List<Tuple> tuples = bannerRepository.findBannerAll();
//            List<Banner> banners = new ArrayList<>();
//            List<File> files = new ArrayList<>();
            List data = new ArrayList();
            for (Tuple tuple : tuples) {
                Banner banner = tuple.get(0, Banner.class); // 첫 번째 필드로 Banner 엔티티를 가져옴
                File file = tuple.get(1, File.class);       // 두 번째 필드로 File 엔티티를 가져옴
                ObjectMapper bannermapper = new ObjectMapper();
                Map<String, Object> totmap =  bannermapper.convertValue(banner, Map.class);
                ObjectMapper filemapper = new ObjectMapper();
                totmap.putAll(filemapper.convertValue(file, Map.class));
                data.add(totmap);
//                banners.add(bannermapper);
//                files.add(filemapper);
//                for(Object o : totmap.values()) {
//                    System.out.println(o);
//                }
//                System.out.println("Banner: " + bannermapper.toString());
//                System.out.println("File: " + filemapper.toString());

            }
//            map.put("banner",banners);
//            map.put("file",files);
            return new ResponseEntity<List>(data, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List>(HttpStatus.BAD_REQUEST);
        }
    }
}
