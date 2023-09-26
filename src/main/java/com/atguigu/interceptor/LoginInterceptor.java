package com.atguigu.interceptor;

import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration) {
            Result r = Result.build(null, ResultCodeEnum.NOTLOGIN);
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(r);
            response.getWriter().println(s);
            return false;
        }

        return true;
    }
}