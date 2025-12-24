package ru.faickoff.api.auth.controller.api.v1.user.self;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import ru.faickoff.api.auth.service.user.SelfUserService;

@RestController
@RequestMapping("/api/v1/users/self")
@RequiredArgsConstructor
public class SelfUserController {

    private final LoggerHttpServletRequestService logger;
    private final SelfUserService selfUserService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserResponse> getSelfUser(HttpServletRequest servletRequest) {
        this.logger.info(servletRequest);
        User selfUser = this.selfUserService.getSelfUser();
        UserResponse responseBody = this.userMapper.toUserResponse(selfUser);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PutMapping
    public ResponseEntity<UserResponse> putSelfUser(
            HttpServletRequest servletRequest,
            @Valid @RequestBody UserPutRequest request) {
        this.logger.info(servletRequest);
        User updating = this.userMapper.toUser(request);
        User updated = this.selfUserService.putSelfUser(updating);
        UserResponse responseBody = this.userMapper.toUserResponse(updated);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PatchMapping
    public ResponseEntity<UserResponse> patchSelfUser(
            HttpServletRequest servletRequest,
            @Valid @RequestBody UserPatchRequest request) {
        this.logger.info(servletRequest);
        User updating = this.userMapper.toUser(request);
        User updated = this.selfUserService.patchSelfUser(updating);
        UserResponse responseBody = this.userMapper.toUserResponse(updated);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSelfUser(HttpServletRequest servletRequest) {
        this.logger.info(servletRequest);
        this.selfUserService.deleteSelfUser();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
