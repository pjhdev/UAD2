package com.uad2.application.config;
import com.uad2.application.exception.SentryExceptionResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
/*
 * @USER JungHyun
 * @DATE 2019-09-22
 * @DESCRIPTION 환경설정
 */

import com.uad2.application.common.interceptor.CommonInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final CommonInterceptor commonInterceptor;
    @Autowired
    public WebConfig(CommonInterceptor commonInterceptor){
        this.commonInterceptor = commonInterceptor;
    }
    @Bean
    public ModelMapper modelMapper() {
        //DTO와 같은 클래스들의 서로간 변환을 담당
        return new ModelMapper();
    }

    /**
     * 인터셉터 등록
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor)
                .excludePathPatterns("/api/member/login");  // 핸들러 인터셉터에서 로그인 URI 제외
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    public HandlerExceptionResolver sentryExceptionResolver() {
        return new SentryExceptionResolver();
    }
}
