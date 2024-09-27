package org.tcebi.lime_mart.domain.dto.security;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Модель токена")
public class JwtAuthenticationResponseDto {

    @Schema(description = "JWT-токен")
    private String token;
}
