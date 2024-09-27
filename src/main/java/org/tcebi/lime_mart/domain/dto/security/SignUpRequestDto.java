package org.tcebi.lime_mart.domain.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Модель запроса на регистрацию")
public class SignUpRequestDto {

    @Schema(description = "Имя пользователя")
    @Size(min = 3, max = 20, message = "Имя пользователя должно содержать от 3 до 20 символов")
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @JsonProperty("user_name")
    private String userName;

    @Schema(description = "Электронная почта пользователя")
    @Size(min = 5, max = 100, message = "Электронная почта должна быть в пределах от 5 до 100 символов")
    @NotBlank(message = "Электронная почта не должна быть пустой")
    @Email(message = "Почта должна быть в форме user@email.ru")
    private String email;

    @Schema(description = "Пароль пользователя")
    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, max = 255, message = "Пароль не должен быть свыше 255 символов")
    private String password;
}
