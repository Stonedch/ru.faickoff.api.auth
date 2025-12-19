package ru.faickoff.api.auth.controller.api.v1.user.self;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.service.logger.LoggerHttpServletRequestService;

@RestController
@RequestMapping("/api/v1/users/self")
@RequiredArgsConstructor
public class SelfUserController {

    private final LoggerHttpServletRequestService logger;

    @GetMapping
    public ResponseEntity<Void> getSelfUser(HttpServletRequest servletRequest) {
        this.logger.info(servletRequest);
        throw new UnsupportedOperationException("Not implemented");
    }

    @PutMapping
    public ResponseEntity<Void> putSelfUser(HttpServletRequest servletRequest) {
        this.logger.info(servletRequest);
        throw new UnsupportedOperationException("Not implemented");
    }

    @PatchMapping
    public ResponseEntity<Void> patchSelfUser(HttpServletRequest servletRequest) {
        this.logger.info(servletRequest);
        throw new UnsupportedOperationException("Not implemented");
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSelfUser(HttpServletRequest servletRequest) {
        this.logger.info(servletRequest);
        throw new UnsupportedOperationException("Not implemented");
    }
}
