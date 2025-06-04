package br.com.backend.interceptor;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Enumeration;
import br.com.backend.service.RequestLoggerService;

@Component
public class RequestLoggerFilter implements Filter {

    private final RequestLoggerService requestLoggerService;

    public RequestLoggerFilter(RequestLoggerService requestLoggerService) {
        this.requestLoggerService = requestLoggerService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            CachedBodyHttpServletRequest cachedRequest = new CachedBodyHttpServletRequest((HttpServletRequest) request);
            String method = cachedRequest.getMethod();
            String uri = cachedRequest.getRequestURI();
            StringBuilder headersBuilder = new StringBuilder();
            Enumeration<String> headerNames = cachedRequest.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                headersBuilder.append(headerName)
                        .append(": ")
                        .append(cachedRequest.getHeader(headerName))
                        .append("\n");
            }
            String headers = headersBuilder.toString();
            String body = cachedRequest.getCachedBodyAsString();
            requestLoggerService.logRequest(method, uri, headers, body);
            chain.doFilter(cachedRequest, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
