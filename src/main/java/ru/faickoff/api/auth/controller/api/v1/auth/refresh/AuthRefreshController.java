package ru.faickoff.api.auth.controller.api.v1.auth.refresh;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.service.logger.LoggerHttpServletRequestService;

@RestController
@RequestMapping("/api/v1/auth/refresh")
@RequiredArgsConstructor
public class AuthRefreshController {

    private final LoggerHttpServletRequestService logger;

    @PostMapping
    public ResponseEntity<Void> refresh(HttpServletRequest servletRequest) {
        this.logger.info(servletRequest);
        throw new UnsupportedOperationException("Not implemented");
    }
}
