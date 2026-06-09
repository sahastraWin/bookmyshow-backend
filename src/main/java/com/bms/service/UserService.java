package com.bms.service;
import com.bms.dto.LoginRequest;
import com.bms.dto.UserRegisterRequest;
import com.bms.dto.UserResponse;
import com.bms.entity.User;
import java.util.List;

public interface UserService {
    UserResponse register(UserRegisterRequest request);
    UserResponse login(LoginRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    User findEntityById(Long id);
}
