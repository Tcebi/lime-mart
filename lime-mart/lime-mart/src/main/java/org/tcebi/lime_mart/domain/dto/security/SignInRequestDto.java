package org.tcebi.lime_mart.domain.dto.security;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Модель запроса авторизации")
public class SignInRequestDto {

    @Schema(description = "Имя пользователя")
    @Size(min = 3, max = 20, message = "Имя пользователя должно содержать от 3 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String username;

    @Schema(description = "Пароль пользователя")
    @Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;
}
