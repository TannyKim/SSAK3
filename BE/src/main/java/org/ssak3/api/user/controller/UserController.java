package org.ssak3.api.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssak3.api.badge.entity.OriginBadge;
import org.ssak3.api.user.entity.User;
import org.ssak3.api.user.service.UserService;

import java.util.List;

@Tag(name="User REST API", description = "User REST API입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ledger")
public class UserController {

    @Autowired
    private final UserService userService;

    @Operation(summary = "유저 조회", description = "유저를 조회합니다.")
    @GetMapping("/user")
    public ResponseEntity<User> userById(@Parameter(name="userId") Long userId) {

        User user;
        try {
            log.info("start select user by id");
            user = userService.findUserById(userId);
        } catch (Exception e) {
            log.error("error", e);
            throw new RuntimeException(e);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
