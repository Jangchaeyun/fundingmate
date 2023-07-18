package com.fund.fundingmate.global.config.security;

//import com.fund.fundingmate.global.oauth.handler.OAuth2AuthenticationFailureHandler;
//import com.fund.fundingmate.global.oauth.handler.OAuth2AuthenticationSuccessHandler;
//import com.fund.fundingmate.global.oauth.handler.TokenAccessDeniedHandler;
//import com.fund.fundingmate.global.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
//import com.fund.fundingmate.global.oauth.service.CustomOAuth2UserService;
//import com.fund.fundingmate.global.oauth.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final CorsProperties corsProperties;
//    private final AppProperties appProperties;
//    private final AuthTokenProvider tokenProvider;
//    private final CustomUserDetailsService userDetailsService;
//    private final CustomOAuth2UserService oAuth2UserService;
//    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
//    private final UserRefreshTokenRepository userRefreshTokenRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    //암호화에 필요한 PasswordEncoder Bean 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    // authenticationManager를 Bean 등록
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    public void  configure(HttpSecurity http) throws  Exception {
        
//        http.csrf().disable(); //csrf 보안 해제
//        http.httpBasic().disable() // 일반적인 루트가 아닌 다른 방식으로 요청시 거절,  header에 id, pw가 아닌 token(jwt)을 달고 간다. 그래서 basic이 아닌 bearer를 사용한다.
//                .authorizeHttpRequests() // 요청에 대한 사용권한 체크
//                .antMatchers("/admin/**").hasRole("ADMIN") // 해당 URL로 요청 시 설정 해준다.
//                .antMatchers("/user/**").hasRole("USER") // andMatchers에 속해있는 URL로 요청이 들어오면 권한 확인
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated(); // andMatchers에 속해있는 URL로 요청이 오면 인증 필요 설정
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션을 사용하지 않는다고 설정한다.
        http
                //h2 콘솔 사용할시
                .csrf().disable().headers().frameOptions().disable()
                .and()

                //세션 사용 안함
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                //URL 관리
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN") // 해당 URL로 요청 시 설정 해준다.
                .antMatchers("/user/**").hasRole("USER") // andMatchers에 속해있는 URL로 요청이 들어오면 권한 확인
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()

                // JwtAuthenticationFilter를 먼저 적용
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//        http
//                // 다른 보안 설정
//                .rememberMe()
//                .tokenValiditySeconds(86400) // Remember Me 토큰의 유효기간 설정 (예: 1일)
//                .key("uniqueAndSecretKey") // Remember Me 토큰에 사용될 고유한 키 설정
//                .rememberMeParameter("remember-me") // Remember Me 토큰 전달을 위한 파라미터 이름 설정
//                .rememberMeCookieName("remember-me-cookie") // Remember Me 토큰을 저장할 쿠키 이름 설정
//                .userDetailsService(userDetailsService()) // 사용자 정보를 가져올 UserDetailsService 설정
//                .and()
//                // 다른 보안 설정
//                .formLogin()
//                // 로그인 페이지 및 로그인 요청 경로 설정
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/dashboard")
//                .permitAll()
//                .and()
//                // 다른 보안 설정
//                .logout()
//                // 로그아웃 경로 및 성공 후 리다이렉션 설정
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
//                .permitAll();
    }
//    /*
//     * UserDetailsService 설정
//     * */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .csrf().disable()
//                .formLogin().disable()
//                .httpBasic().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
//                .accessDeniedHandler(tokenAccessDeniedHandler)
//                .and()
//                .authorizeRequests()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                .antMatchers("/api/**").hasAnyAuthority(RoleType.USER.getCode())
//                .antMatchers("/api/**/admin/**").hasAnyAuthority(RoleType.ADMIN.getCode())
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login()
//                .authorizationEndpoint()
//                .baseUri("/oauth2/authorization")
//                .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
//                .and()
//                .redirectionEndpoint()
//                .baseUri("/*/oauth2/code/*")
//                .and()
//                .userInfoEndpoint()
//                .userService(oAuth2UserService)
//                .and()
//                .successHandler(oAuth2AuthenticationSuccessHandler())
//                .failureHandler(oAuth2AuthenticationFailureHandler());
//
//        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//
//    /*
//     * auth 매니저 설정
//     * */
//    @Override
//    @Bean(BeanIds.AUTHENTICATION_MANAGER)
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    /*
//     * security 설정 시, 사용할 인코더 설정
//     * */
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /*
//     * 토큰 필터 설정
//     * */
//    @Bean
//    public TokenAuthenticationFilter tokenAuthenticationFilter() {
//        return new TokenAuthenticationFilter(tokenProvider);
//    }
//
//    /*
//     * 쿠키 기반 인가 Repository
//     * 인가 응답을 연계 하고 검증할 때 사용.
//     * */
//    @Bean
//    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
//        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
//    }
//
//    /*
//     * Oauth 인증 성공 핸들러
//     * */
//    @Bean
//    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
//        return new OAuth2AuthenticationSuccessHandler(
//                tokenProvider,
//                appProperties,
//                userRefreshTokenRepository,
//                oAuth2AuthorizationRequestBasedOnCookieRepository()
//        );
//    }
//
//    /*
//     * Oauth 인증 실패 핸들러
//     * */
//    @Bean
//    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
//        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
//    }
//
//    /*
//     * Cors 설정
//     * */
//    @Bean
//    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
//        corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
//        corsConfig.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins().split(",")));
//        corsConfig.setAllowCredentials(true);
//        corsConfig.setMaxAge(corsConfig.getMaxAge());
//
//        corsConfigSource.registerCorsConfiguration("/**", corsConfig);
//        return corsConfigSource;
//    }
}
