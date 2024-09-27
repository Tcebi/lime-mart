package org.tcebi.lime_mart.service.security.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.tcebi.lime_mart.domain.entity.security.User;
import org.tcebi.lime_mart.repository.security.UserRepository;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User create(User user) {
        if (userRepository.existsByUserName(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException(("Пользователь с такой электронной почтой уже существует"));
        }
        return save(user);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("Такого пользователя не существует"));
    }

    public UserDetailsService userDetailsService() {
        return this::findByUserName;
    }

    public User getCurrentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByUserName(name);
    }

}
