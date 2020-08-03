package com.streamit.streamitdemo.interceptors;

import com.streamit.streamitdemo.model.entity.User;
import com.streamit.streamitdemo.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class UploadLimitInterceptor implements HandlerInterceptor {

    private final UserService userService;

    public UploadLimitInterceptor(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userService.findByUsername(user.getUsername()).getSongs().size() >= 3) {
            request.setAttribute("uploadLimit", true);
        }
    }
}
