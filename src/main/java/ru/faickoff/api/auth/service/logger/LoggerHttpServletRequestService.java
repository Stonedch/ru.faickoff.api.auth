package ru.faickoff.api.auth.service.logger;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.faickoff.api.auth.dto.logger.LoggerHttpServletRequest;
import ru.faickoff.api.auth.mapper.logger.LoggerHttpServletRequestMapper;

@Service
@Log4j2
@RequiredArgsConstructor
public class LoggerHttpServletRequestService {

    private final LoggerHttpServletRequestMapper loggerHttpServletRequestMapper;

    public void info(HttpServletRequest httpServletRequest, String requestBody) {
        LoggerHttpServletRequest mapped = loggerHttpServletRequestMapper
                .toLoggerHttpServletRequest(httpServletRequest, requestBody);
        LoggerHttpServletRequestService.log.info(mapped);
    }

    public void info(HttpServletRequest httpServletRequest) {
        LoggerHttpServletRequest mapped = loggerHttpServletRequestMapper
                .toLoggerHttpServletRequest(httpServletRequest);
        LoggerHttpServletRequestService.log.info(mapped);
    }
}
