package com.math.game.Service;

import com.math.game.DTO.UserDTO;
import com.math.game.Entity.User;
import com.math.game.Exeption.UserAlreadyExistsExeption;
import com.math.game.Repository.UserRepository;
import com.math.game.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsExeption("User with this email already exists");
        }

        userRepository.save(user);
    }

    public User findUser(String email) {
        if (email == null || email.isEmpty()) {
            return null;
        }
        return userRepository.findByEmail(email);
    }
}