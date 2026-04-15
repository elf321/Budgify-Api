package com.example.Budgify_Api.services.user;

import com.example.Budgify_Api.entities.User;
import com.example.Budgify_Api.repos.UserRepository;
import com.example.Budgify_Api.services.user.dtos.UserDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO.UserResponse registerUser(UserDTO.UserRegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setSurname(request.getSurname());

        user.setHashedPassword(request.getPassword());

        User savedUser = userRepository.save(user);
        return convertToResponse(savedUser);
    }

    public UserDTO.UserResponse loginUser(UserDTO.UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if(!user.getHashedPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }

        return convertToResponse(user);
    }

    public List<UserDTO.UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private UserDTO.UserResponse convertToResponse(User user) {
        UserDTO.UserResponse response = new UserDTO.UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFullName(user.getName() + " " + user.getSurname());
        return response;
    }
}
