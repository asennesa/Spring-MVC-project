package com.streamit.streamitdemo.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class UploadTimeLogInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(UploadTimeLogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        if (request.getMethod().equals("POST")) {
            logger.info("Request URL::" + request.getRequestURL().toString()
                    + ":: Start Time=" + System.currentTimeMillis());
            request.setAttribute("startTime", startTime);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (request.getMethod().equals("POST")) {
            long startTime = (Long) request.getAttribute("startTime");
            logger.info("Request URL::" + request.getRequestURL().toString()
                    + ":: End Time=" + System.currentTimeMillis());
            logger.info("Request URL::" + request.getRequestURL().toString()
                    + ":: Time Taken=" + (System.currentTimeMillis() - startTime));

        }

    }
}