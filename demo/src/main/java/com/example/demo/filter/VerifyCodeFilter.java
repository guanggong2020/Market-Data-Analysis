package com.example.demo.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class VerifyCodeFilter extends GenericFilterBean {
    private String defaultFilterProcessUrl = "/doLogin";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if ("POST".equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath())) {
            // 验证码验证
            String requestCaptcha = request.getParameter("verifycode");
            String genCaptcha = (String) request.getSession().getAttribute("validate-code");
            try {
                if (StringUtils.isEmpty(requestCaptcha)) {
                    throw new AuthenticationServiceException("验证码不能为空");
                }
                if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
                    throw new AuthenticationServiceException("验证码/用户/密码 错误");
                }
            } catch (AuthenticationServiceException e) {
                request.getSession().setAttribute("message", e.getMessage());
                // 验证码不通过，重定向到登录页
                response.sendRedirect("/login");
                return;
            }
            // 校验正确后，移除session中验证码
            request.getSession(false).removeAttribute("validate-code");
        }
        chain.doFilter(request, response);
    }
}
