package br.com.backend.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestLoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        CachedBodyHttpServletRequest cachedRequest = new CachedBodyHttpServletRequest(request);
        // Log request details
        System.out.println("Request URI: " + cachedRequest.getRequestURI());
        System.out.println("Request Method: " + cachedRequest.getMethod());
        System.out.println("Request Body: " + cachedRequest.getCachedBodyAsString());
        // Continue with the next interceptor or the handler
        return true;
    }

}
