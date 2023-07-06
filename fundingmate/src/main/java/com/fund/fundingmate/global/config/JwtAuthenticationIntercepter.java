package com.fund.fundingmate.global.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtAuthenticationIntercepter implements HandlerInterceptor {
//    //해당 클래스는 JwtTokenProvider가 검증을 끝낸 Jwt로부터 유저 정보를 조회해와서 UserPasswordAuthenticationFilter 로 전달합니다.
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws Exception {
//        //1. reqyuest의 header로부터 토큰을 자져온다.
//        String token = jwtTokenProvider.resolveToken(request);
//        System.out.println("token:"+token);
////		if (token == null) {
////			if (request.getRequestURI().equals("/login")) //토큰이 비어있고 로그인 요청이면 패스한다.
////				return true;
////			else {  //로그인이 아니면서 토큰이 비어있으면 로그인 요청한다.
////				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////				response.getWriter().write("{\"rescode\":102}");
////				response.getWriter().flush();
////				return false;
////			}
////		}
//        if(token==null) return true; //토큰이 비어있으면 로그인 처리로 패스한다.
//        String[] tokens = token.split(","); //token[0]:access token,token[1]:refresh token
//        if(jwtTokenProvider.validateToken(tokens[0])) { //2.access token이 유효한 경우 정상처리한다.
//            System.out.println("2.access token이 유효한 경우 정상처리한다.");
//            //토큰이 유효하면 토큰으로부터 유저정보를 가져와 Security Context에 Autnendication을 저장한다.
//            Authentication authentication = jwtTokenProvider.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            return true; //
//        } else if(tokens.length==1) { //3.access token만 가져왔는데 유효하지 않아서 refresh token 요청
//            System.out.println("3.access token만 가져왔는데 유효하지 않아서 refresh token 요청");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("{\"rescode\":100}");
//            response.getWriter().flush();
//        } else if(jwtTokenProvider.validateToken(tokens[1])) { //4.refresh token이 유효함. 새로운 두개의 토큰 재발급해줌
//            System.out.println("4.refresh token이 유효함. 새로운 두개의 토큰 재발급해줌");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            String userPk = jwtTokenProvider.getUserPk(tokens[1]);
//            String accessToken = jwtTokenProvider.createToken(userPk,)
//            String refreshToken = jwtTokenProvider.refreshToken(userPk);
//            //response.getWriter().write("{\"rescode\":101}");
//            response.getWriter().write("{\"rescode\":101,\"accessToken\":\""+accessToken+"\",\"refreshToken\":\""+refreshToken+"\"}");
//            response.getWriter().flush();
//        } else { //5.refresh token 만료됨. 재로그인 요청
//            System.out.println("5.refresh token 만료됨. 재로그인 요청");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("{\"rescode\":102}");
//            response.getWriter().flush();
//        }
//        return false;
//    }
}
