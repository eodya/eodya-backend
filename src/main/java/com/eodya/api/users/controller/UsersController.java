package com.eodya.api.users.controller;

import com.eodya.api.users.dto.request.UserLoginRequest;
import com.eodya.api.users.dto.response.UserLoginResponse;
import com.eodya.api.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UsersController {

    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<UserLoginResponse> loginAndSignIn(
            @Valid @RequestBody UserLoginRequest request
    ) {
        UserLoginResponse response = userService.login(request.getToken());
        return ResponseEntity.ok().body(response);
    }

}
