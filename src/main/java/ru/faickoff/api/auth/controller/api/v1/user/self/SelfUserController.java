package ru.faickoff.api.auth.controller.api.v1.user.self;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.faickoff.api.auth.dto.request.user.UserPatchRequest;
import ru.faickoff.api.auth.dto.request.user.UserPutRequest;
import ru.faickoff.api.auth.dto.response.user.UserResponse;
import ru.faickoff.api.auth.mapper.user.UserMapper;
import ru.faickoff.api.auth.model.User;
import ru.faickoff.api.auth.service.logger.LoggerHttpServletRequestService;
import ru.faickoff.api.auth.service.user.SelfUserResponseService;
import ru.faickoff.api.auth.service.user.SelfUserService;

@RestController
@RequestMapping("/api/v1/users/self")
@RequiredArgsConstructor
public class SelfUserController {

    private final LoggerHttpServletRequestService logger;
    private final UserMapper userMapper;
    private final SelfUserService selfUserService;
    private final SelfUserResponseService selfUserResponseService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<UserResponse> getSelfUser(HttpServletRequest servletRequest) {
        this.logger.info(servletRequest);
        UserResponse responseBody = this.selfUserResponseService.getSelfUser();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<UserResponse> putSelfUser(
            HttpServletRequest servletRequest,
            @Valid @RequestBody UserPutRequest request) {
        this.logger.info(servletRequest);
        User updating = this.userMapper.toUser(request);
        UserResponse responseBody = this.selfUserResponseService.putSelfUser(updating);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PatchMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<UserResponse> patchSelfUser(
            HttpServletRequest servletRequest,
            @Valid @RequestBody UserPatchRequest request) {
        this.logger.info(servletRequest);
        User updating = this.userMapper.toUser(request);
        UserResponse responseBody = this.selfUserResponseService.patchSelfUser(updating);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Void> deleteSelfUser(HttpServletRequest servletRequest) {
        this.logger.info(servletRequest);
        this.selfUserService.deleteSelfUser();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
