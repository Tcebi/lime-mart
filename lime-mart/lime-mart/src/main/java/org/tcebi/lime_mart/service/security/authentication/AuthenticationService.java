package org.tcebi.lime_mart.service.security.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tcebi.lime_mart.domain.dto.security.JwtAuthenticationResponseDto;
import org.tcebi.lime_mart.domain.dto.security.SignInRequestDto;
import org.tcebi.lime_mart.domain.dto.security.SignUpRequestDto;
import org.tcebi.lime_mart.domain.entity.security.User;
import org.tcebi.lime_mart.domain.enums.security.Role;
import org.tcebi.lime_mart.service.security.jwt.JwtService;
import org.tcebi.lime_mart.service.security.users.UsersService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UsersService usersService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        User user = new User();
        user.setUserName(signUpRequestDto.getUserName());
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setRole(Role.ROLE_USER);

        usersService.create(user);

        String jwtToken = jwtService.generateToken(user);
        return new JwtAuthenticationResponseDto(jwtToken);
    }

    public JwtAuthenticationResponseDto signIn(SignInRequestDto signInRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequestDto.getUsername(),
                signInRequestDto.getPassword()
        ));

        UserDetails userDetails = usersService.userDetailsService().loadUserByUsername(signInRequestDto.getUsername());

        String jwtToken = jwtService.generateToken(userDetails);

        return new JwtAuthenticationResponseDto(jwtToken);
    }
}
