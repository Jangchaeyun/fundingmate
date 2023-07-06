package com.fund.fundingmate.global.config;

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
public class SecurityConfig extends WebSecurityConfigurerAdapter {
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
    }
}
