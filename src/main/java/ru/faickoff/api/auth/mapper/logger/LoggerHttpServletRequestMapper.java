package ru.faickoff.api.auth.mapper.logger;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import ru.faickoff.api.auth.dto.logger.LoggerHttpServletRequest;

@Component
public class LoggerHttpServletRequestMapper {

    public LoggerHttpServletRequest toLoggerHttpServletRequest(
            HttpServletRequest httpServletRequest,
            String requestBody) {
        return LoggerHttpServletRequest.builder()
                .remoteHost(httpServletRequest.getRemoteHost())
                .headerUserAgent(httpServletRequest.getHeader("User-Agent"))
                .headerReferer(httpServletRequest.getHeader("Referer"))
                .requestURL(httpServletRequest.getRequestURL().toString())
                .requestURI(httpServletRequest.getRequestURI())
                .queryString(httpServletRequest.getQueryString())
                .method(httpServletRequest.getMethod())
                .requestBody(requestBody)
                .build();
    }

    public LoggerHttpServletRequest toLoggerHttpServletRequest(
            HttpServletRequest httpServletRequest) {
        return this.toLoggerHttpServletRequest(httpServletRequest, null);
    }
}
