package org.tcebi.lime_mart.controller.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tcebi.lime_mart.domain.dto.security.JwtAuthenticationResponseDto;
import org.tcebi.lime_mart.domain.dto.security.SignInRequestDto;
import org.tcebi.lime_mart.domain.dto.security.SignUpRequestDto;
import org.tcebi.lime_mart.service.security.authentication.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthRestControllerV1 {

    private final AuthenticationService authService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponseDto signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        return authService.signUp(signUpRequestDto);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponseDto signIn(@RequestBody @Valid SignInRequestDto request) {
        return authService.signIn(request);
    }
}
