package cn.iocoder.yudao.module.system.controller;

import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        String method = request.getMethod();

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
