package com.bms.service;
import com.bms.dto.LoginRequest;
import com.bms.dto.UserRegisterRequest;
import com.bms.dto.UserResponse;
import com.bms.entity.User;
import com.bms.exception.BusinessException;
import com.bms.exception.ResourceNotFoundException;
import com.bms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse register(UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email already in use: " + request.getEmail());
        }
        User user = User.builder()
                .name(request.getName()).email(request.getEmail())
                .password(request.getPassword())   // NOTE: hash in production with BCrypt
                .phoneNumber(request.getPhoneNumber())
                .build();
        return toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException("Invalid email or password"));
        // NOTE: use BCrypt.matches() in production
        if (!user.getPassword().equals(request.getPassword())) {
            throw new BusinessException("Invalid email or password");
        }
        return toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        return toResponse(findEntityById(id));
    }

    @Override
    public User findEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    private UserResponse toResponse(User u) {
        return UserResponse.builder()
                .id(u.getId()).name(u.getName()).email(u.getEmail())
                .phoneNumber(u.getPhoneNumber()).createdAt(u.getCreatedAt())
                .build();
    }
}
