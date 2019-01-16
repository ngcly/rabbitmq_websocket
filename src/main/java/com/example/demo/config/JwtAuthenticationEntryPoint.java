package com.example.demo.config;

import com.example.demo.util.RestCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义未认证401返回值
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        RestCode restCode = RestCode.UNAUTHEN;
        if("Full authentication is required to access this resource".equals(e.getMessage())){
        }else if("Bad credentials".equals(e.getMessage())){
            restCode = RestCode.USER_ERR;
        }else if("User is disabled".equals(e.getMessage())){
            restCode = RestCode.USER_DISABLE;
        }
        String url = "  登录地址： http://"+request.getServerName()+":"+request.getServerPort()+"/login";
        out.write("{\"code\":"+restCode.code+",\"msg\":\""+restCode.msg+url+"\"}");
        out.flush();
        out.close();
    }
}
