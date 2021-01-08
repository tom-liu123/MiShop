package com.itqf.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author LiuCongYang
 * @Version 1.0.0
 * create at @date  2021/1/8 11:42
 * copyright Beijing Murong Information Technology Co.,Ltd.
 */

@WebFilter("/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("utf-8");
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
