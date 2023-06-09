package com.ng.todo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/*
 * 如果是一个过滤器,在MyFilter类上加上@WebFilter(filterName = "myFilter",urlPatterns = {"/*"}) ,入口类加上@ServletComponentScan即可
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(request.getParameter("name"));
        HttpServletRequest hrequest = (HttpServletRequest)request;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
        if(hrequest.getRequestURI().indexOf("/index") != -1 ||
                hrequest.getRequestURI().indexOf("/asd") != -1 ||
                hrequest.getRequestURI().indexOf("/online") != -1 ||
                hrequest.getRequestURI().indexOf("/login") != -1
        ) {
            chain.doFilter(request, response);
        }else {
//            wrapper.sendRedirect("/login");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
