package com.northsea.config;

import com.northsea.handler.*;
//import com.northsea.service.impl.UserDetailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/18
 * @Description
 */
@Slf4j
@Configuration
//@EnableWebSecurity   // 如果有找不到HttpSecurity类型Bean的异常，就添加这个注解
public class SpringSecurityConfig{

    private static final String[] IGNORE_API = new String[]{ "/login"};

//    @Autowired
//    private UserDetailServiceImpl userDetailService;

//    @Autowired
//    AuthenticationEnryPoint authenticationEnryPoint;    //未登录
//    @Autowired
//    AuthenticationSuccess authenticationSuccess;    //登录成功
//    @Autowired
//    AuthenticationFailure authenticationFailure;    //登录失败
//    @Autowired
//    AuthenticationLogout authenticationLogout;      //注销
//    @Autowired
//    AccessDeny accessDeny;      //无权访问
//    @Autowired
//    SessionInformationExpiredStrategy sessionInformationExpiredStrategy;    //检测异地登录
//    @Autowired
//    SelfAuthenticationProvider selfAuthenticationProvider;      //自定义认证逻辑处理



    //配置过滤
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authz ->
//                        // 放行一些接口,在 IGNORE_API 中
//                        authz.antMatchers(IGNORE_API).permitAll()
//                                // 除了放行的接口其他全校验
//                                .anyRequest().authenticated()
//                )
//                // 使用 Spring Security 提供的默认值启用安全功能
//                .httpBasic(withDefaults());
//        return http.build();
//    }

    /**
     * 密码明文加密方式配置
     * @return
     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    //认证
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(selfAuthenticationProvider);
//    }

    /**
     * 配置跨源访问(CORS)
     * @return
     */
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }

}
