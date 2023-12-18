package org.ssak3.api.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ssak3.api.user.dto.response.UserResponse;
import org.ssak3.api.user.entity.User;
import org.ssak3.api.user.service.UserService;

@Tag(name="User REST API", description = "User REST API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 조회", description = "유저를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 조회 성공", content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "유저 조회 실패")
    })
    @PostMapping("")
    public ResponseEntity<?> userById(@RequestParam Long userId) throws Exception {
        User user = userService.findUserById(userId);
        if (user == null) {
            throw (new Exception("유저 아이디를 확인해주세요"));
        }
        UserResponse userResponse = new UserResponse(user);
        return ResponseEntity.status(200).body(userResponse);
    }

    @Operation(summary = "유저 생성", description = "유저를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 생성 성공", content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "유저 생성 실패")
    })
    @PostMapping("/create")
    public ResponseEntity<?> userAdd(@RequestParam boolean isNewUser) throws Exception {
        if (!isNewUser) {
            throw (new Exception("등록된 유저인지 확인해주세요"));
        }
        User newUser = userService.insertUser();
        UserResponse userResponse = new UserResponse(newUser);
        return ResponseEntity.status(200).body(userResponse);
    }
}
