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

    public UserDTO.Response createUser(UserDTO.CreateRequest request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setHashedPassword(request.getPassword());
        user.setName(request.getName());
        user.setSurname(request.getSurname());

        User savedUser = userRepository.save(user);
        return convertToResponse(savedUser);
    }

    public List<UserDTO.Response> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private UserDTO.Response convertToResponse(User user) {
        UserDTO.Response response = new UserDTO.Response();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFullName(user.getName() + " " + user.getSurname());
        return response;
    }
}
