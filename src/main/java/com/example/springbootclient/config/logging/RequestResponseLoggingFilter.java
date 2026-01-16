package com.example.springbootclient.config.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        long start = System.currentTimeMillis();
        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            long durationMs = System.currentTimeMillis() - start;

            String method = request.getMethod();
            String uri = request.getRequestURI();
            String query = request.getQueryString();

            String requestBody = getBody(wrappedRequest.getContentAsByteArray());
            String responseBody = getBody(wrappedResponse.getContentAsByteArray());

            int status = wrappedResponse.getStatus();

            log.info("REQUEST method={} uri={} query={} body={}", method, uri, query, requestBody);
            log.info("RESPONSE status={} durationMs={} body={}", status, durationMs, responseBody);

            wrappedResponse.copyBodyToResponse();
        }
    }

    private String getBody(byte[] bytes) {
        if (bytes == null || bytes.length == 0) return "";
        String s = new String(bytes, StandardCharsets.UTF_8);
        // avoid huge logs
        if (s.length() > 4000) return s.substring(0, 4000) + "...(truncated)";
        return s;
    }
}