package ru.itis.antonov.cms.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!request.getServletPath().equals("/editor")){
            return true;
        }
        if(request.getSession().getAttribute("auth") == null){
            response.sendRedirect(request.getContextPath() + "/signIn");
            return false;
        }
        boolean auth = (boolean) request.getSession().getAttribute("auth");
        if(!auth){
            response.sendRedirect(request.getContextPath() + "/signIn");
            return false;
        }
        return true;
    }
}
