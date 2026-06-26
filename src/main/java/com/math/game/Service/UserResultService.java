package com.math.game.Service;
import com.math.game.DTO.UserResultDTO;
import com.math.game.DTO.UserResultResponseDTO;
import com.math.game.Entity.User;
import com.math.game.Entity.UserResult;
import com.math.game.Repository.UserRepository;
import com.math.game.Repository.UserResultRepository;
import lombok.AllArgsConstructor;
import com.math.game.mapper.UserResultMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserResultService {

    private final UserResultRepository userResultRepository;
    private final UserRepository userRepository;
    private final UserResultMapper userResultMapper;

    public void saveResult(UserResultDTO userResultDTO) {
        UserResult userResult = userResultMapper.toEntity(userResultDTO);

        User user = userRepository.findById(userResultDTO.getUserId()).orElse(null);
        userResult.setUser(user);

        userResultRepository.save(userResult);
    }

    public List<UserResult> getAllUserResults(){
        return userResultRepository.findAll();
    }

    public List<UserResultResponseDTO> getAllUserResultsAsDTO(){
        return userResultRepository.findAll().stream()
                .map(userResultMapper::toUserResultResponseDTO)
                .collect(Collectors.toList());
    }

  }

