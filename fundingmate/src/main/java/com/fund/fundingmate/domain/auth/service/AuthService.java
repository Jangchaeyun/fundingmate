package com.fund.fundingmate.domain.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fund.fundingmate.domain.auth.common.KakaoUserInfoResponse;
import com.fund.fundingmate.domain.auth.dto.LoginResponseDto;
import com.fund.fundingmate.domain.auth.dto.TokenDto;
import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import com.fund.fundingmate.domain.user.service.CustomUserDetailService;
import com.fund.fundingmate.global.config.security.JwtTokenProvider;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtTokenProvider jwtTokenPrivider;
//    public String createNaverURL () throws UnsupportedEncodingException {
//        StringBuffer url = new StringBuffer();
//
//        // 카카오 API 명세에 맞춰서 작성
//        String redirectURI = URLEncoder.encode("http://localhost:8080/login/ouath2/code/naver", "UTF-8"); // redirectURI 설정 부분
//        SecureRandom random = new SecureRandom();
//        String state = new BigInteger(130, random).toString();
//
//        url.append("https://nid.naver.com/oauth2.0/authorize?response_type=code");
//        url.append("&client_id=" + "ra3NHpwYEs3KCyDuy1O9");
//        url.append("&state=" + state);
//        url.append("&redirect_uri=" + redirectURI);
//
//    /* 로그인 중 선택 권한 허용 URL로 redirect 문제 해결하기
//       로그인 시도시, "현재 UYouBooDan은 개발 중 상태입니다. 개발 중 상태에서는 등록된 아이디만 로그인할 수 있습니다." 화면으로 가버림.
//       아래와 같은 URL로 리다이렉트 되도록 유도하는 해결책 찾기
//       : https://nid.naver.com/oauth2.0/authorize?client_id=avgLtiDUfWMFfHpplTZh&redirect_uri=https://developers.naver.com/proxyapi/forum/auth/oAuth2&response_type=code&state=RZ760w
//     */
//
//        return url.toString();
//    }
    //    public String getKaKaoAccessToken(String code) {
//
//        String access_Token = "";
//        String refresh_Token = "";
//        String reqURL = "https://kauth.kakao.com/oauth/token";
//
//        try {
//            URL url = new URL(reqURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//
//            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//            StringBuilder sb = new StringBuilder();
//            sb.append("grant_type=authorization_code");
//            sb.append("&client_id=" + "a4759eb6df46f2eda8d168437d5cf8f7"); // TODO REST_API_KEY 입력
//            sb.append("&redirect_uri=" + "http://localhost:8080/login/oauth2/code/kakao"); // TODO 인가코드 받은 redirect_uri 입력
//            sb.append("&code=" + code);
//            sb.append("&client_secret=" + "ZojukSkIJZJvaBXxOyNfSfCAKDiaWeVa"); // 카카오 Dev 카카오 로그인 Client Secret
//            bw.write(sb.toString());
//            bw.flush();
//
//            //결과 코드가 200이라면 성공
//            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode : " + responseCode);
//            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line = "";
//            String result = "";
//
//            while ((line = br.readLine()) != null) {
//                result += line;
//            }
//            System.out.println("response body : " + result);
//
//            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
//            JsonParser parser = new JsonParser();
//            JsonElement element = parser.parse(result);
//
//            access_Token = element.getAsJsonObject().get("access_token").getAsString();
//            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
//
//            System.out.println("access_token : " + access_Token);
//            System.out.println("refresh_token : " + refresh_Token);
//
//            br.close();
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return access_Token;
//    }
    @Transactional
    public TokenDto getKakaoAccessToken(String code) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // Http Response Body 객체 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code"); //카카오 공식문서 기준 authorization_code 로 고정
        params.add("client_id", "a4759eb6df46f2eda8d168437d5cf8f7"); // 카카오 Dev 앱 REST API 키
        params.add("redirect_uri", "http://localhost:3000/login"); // 카카오 Dev redirect uri
        params.add("code", code); // 프론트에서 인가 코드 요청시 받은 인가 코드값
        params.add("client_secret", "ZojukSkIJZJvaBXxOyNfSfCAKDiaWeVa"); // 카카오 Dev 카카오 로그인 Client Secret

        // 헤더와 바디 합치기 위해 Http Entity 객체 생성
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // 카카오로부터 Access token 받아오기
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> accessTokenResponse = rt.exchange(
                "https://kauth.kakao.com/oauth/token", // "https://kauth.kakao.com/oauth/token"
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // JSON Parsing (-> KakaoTokenDto)
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TokenDto kakaoTokenDto = null;
        try {
            kakaoTokenDto = objectMapper.readValue(accessTokenResponse.getBody(), TokenDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return kakaoTokenDto;
    }
    public ResponseEntity<LoginResponseDto> kakaoLogin(String kakaoAccessToken) {
        String userInfoRequestUrl = "https://kapi.kakao.com/v2/user/me";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoAccessToken);
        HttpEntity<String> userInfoRequestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        // 카카오 API 응답을 매핑할 클래스 타입 정의
        ParameterizedTypeReference<KakaoUserInfoResponse> responseType =
                new ParameterizedTypeReference<KakaoUserInfoResponse>() {};

        // GET 요청을 보내고 응답을 받아옴
        ResponseEntity<KakaoUserInfoResponse> userInfoResponse =
                restTemplate.exchange(userInfoRequestUrl, HttpMethod.GET, userInfoRequestEntity, responseType);

        if (userInfoResponse.getStatusCode().is2xxSuccessful()) {
            KakaoUserInfoResponse userInfo = userInfoResponse.getBody();
            User user = new User();
            user.setSnsLogin(String.valueOf(userInfo.getId()));
            Optional<User> osavedUser = userRepository.findBySnsLogin(user.getSnsLogin());
//            if(!osavedUser.isPresent()) {
////            user.setName(userInfo.getProperties().getNickname());
////            user.setEmail(userInfo.getKakaoAccount().getEmail());
//                // 필요한 속성들을 추가로 설정
//
//                userRepository.save(user);
//            }
            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setLoginSuccess(true);
            loginResponseDto.setUser(user);
            if(osavedUser.isPresent()){
                User savedUser = osavedUser.get();
                if(savedUser.getUserid() != null) {
                    User userToken = (User) customUserDetailService.loadUserByUsername(savedUser.getUserid());
                    String accessToken = jwtTokenPrivider.createToken(userToken.getUsername(), userToken.getRoles());
                    String refreshToken = jwtTokenPrivider.refreshToken(userToken.getUsername(), userToken.getRoles());
                    loginResponseDto.setAccessToken(accessToken);
                    loginResponseDto.setAccessToken(refreshToken);
                    loginResponseDto.setUser(savedUser);
                }
            }

            return ResponseEntity.ok().headers(headers).body(loginResponseDto);

        } else {
            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setLoginSuccess(false);
            return ResponseEntity.badRequest().body(loginResponseDto);
        }
//        User user = getKakaoInfo(kakaoAccessToken);
//        System.out.println(user);
//        LoginResponseDto loginResponseDto = new LoginResponseDto();
//        loginResponseDto.setLoginSuccess(true);
//        loginResponseDto.setUser(user);
//
//        User existOwner = userRepository.findBySnsLogin(user.getSnsLogin()).orElse(null);
//        try {
//            if (existOwner == null) {
//                System.out.println("처음 로그인 하는 회원입니다.");
//                userRepository.save(user);
//            }
//            loginResponseDto.setLoginSuccess(true);
//
//           ResponseEntity.ok().headers(headers).body(loginResponseDto);
//           return new ResponseEntity<LoginResponseDto>(HttpStatus.OK);
//
//        } catch (Exception e) {
//            loginResponseDto.setLoginSuccess(false);
//            return ResponseEntity.badRequest().body(loginResponseDto);
//        }
    }
//    public HashMap<String, Object> getUserKakaoInfo(String access_Token) {
//
//        // 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
//        HashMap<String, Object> userInfo = new HashMap<String, Object>();
//        String reqURL = "https://kapi.kakao.com/v2/user/me";
//        try {
//            URL url = new URL(reqURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            // 요청에 필요한 Header에 포함될 내용
//            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
//
//            int responseCode = conn.getResponseCode();
//            System.out.println("responseCode : " + responseCode);
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//            String line = "";
//            String result = "";
//
//            while ((line = br.readLine()) != null) {
//                result += line;
//            }
//            System.out.println("response body : " + result);
//
//            JsonParser parser = new JsonParser();
//            JsonElement element = parser.parse(result);
//
//            String id = element.getAsJsonObject().get("id").getAsString();
//
//            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
//            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//
//            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
//            if(kakao_account.getAsJsonObject().get("email") != null){
//                String email = kakao_account.getAsJsonObject().get("email").getAsString();
//                userInfo.put("email", email);
//            }
//
//            userInfo.put("nickname", nickname);
//            userInfo.put("id", id);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return userInfo;
//    }
    public User getKakaoInfo(String kakaoAccessToken) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoAccessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> accountInfoRequest = new HttpEntity<>(headers);

        // POST 방식으로 API 서버에 요청 후 response 받아옴
        ResponseEntity<String> accountInfoResponse = rt.exchange(
                "https://kapi.kakao.com/v2/user/me", // "https://kapi.kakao.com/v2/user/me"
                HttpMethod.POST,
                accountInfoRequest,
                String.class
        );
        System.out.println(accountInfoResponse);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(accountInfoResponse.getBody().toString());
        String id = jsonObject.get("id").toString();
//        JsonObject kakao_account = (JsonObject) jsonObject.get("kakao_account");
//        JsonObject email = (JsonObject) kakao_account.get("email");
//        System.out.println(email);
       // System.out.println(id.toString());


        // 회원가입 처리하기
//        String kakaoId = kakaoAccountDto.getId();
//        String kakaoemail = kakaoAccountDto.getEmail();
//        System.out.println(kakaoId);
//        System.out.println(kakaoemail);
//        User existOwner = userRepository.findByUserid(kakaoId).orElse(null);
        User existOwner =userRepository.findBySnsLogin(id).orElse(null);
        // 처음 로그인이 아닌 경우
        if (existOwner != null) {
            return User.builder()
                    .snsLogin(id)
//                    .email(kakaoAccountDto.getEmail())
                    .build();
        }
        // 처음 로그인 하는 경우
        else {
            return User.builder()
                    .snsLogin(id)
                    .build();
        }
    }
//public UserKakaoLoginResponseDto kakaoLogin(String access_Token) {
//    UserKakaoSignupRequestDto userKakaoSignupRequestDto = getUserKakaoSignupRequestDto(getUserKakaoInfo(access_Token));
//    UserResponseDto userResponseDto = findByUserKakaoIdentifier(userKakaoSignupRequestDto.getUserKakaoIdentifier());
//    if (userResponseDto == null){
//        signUp(userKakaoSignupRequestDto);
//        userResponseDto = findByUserKakaoIdentifier(userKakaoSignupRequestDto.getUserKakaoIdentifier());
//    }
//
//    String token = jwtTokenProvider.createToken(userResponseDto.getUserEmail());
//    return new UserKakaoLoginResponseDto(HttpStatus.OK, token, userResponseDto.getUserEmail());
//}
}
