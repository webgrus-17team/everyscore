package com.webgrus17.everyscore.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 우선 생성해 둠
// url mapping 하는 클래스이라서, 사용하지 않고 삭제할 수도 있음
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        ;
    }
}
