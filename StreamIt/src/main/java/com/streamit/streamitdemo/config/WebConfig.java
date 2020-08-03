package com.streamit.streamitdemo.config;

import com.streamit.streamitdemo.interceptors.UploadLimitInterceptor;
import com.streamit.streamitdemo.interceptors.UploadTimeLogInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {

    private UploadTimeLogInterceptor uploadTimeLogInterceptor;
    private UploadLimitInterceptor uploadLimitInterceptor;


    public WebConfig(UploadTimeLogInterceptor uploadTimeLogInterceptor, UploadLimitInterceptor uploadLimitInterceptor) {
        this.uploadTimeLogInterceptor = uploadTimeLogInterceptor;
        this.uploadLimitInterceptor = uploadLimitInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(uploadTimeLogInterceptor).addPathPatterns("/songs/upload");
        registry.addInterceptor(uploadLimitInterceptor).addPathPatterns("/songs/upload");

    }
}