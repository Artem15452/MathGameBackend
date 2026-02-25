package com.math.game.Service;
import com.math.game.DTO.UserResultDTO;
import com.math.game.DTO.UserResultResponseDTO;
import com.math.game.DTO.UserDTO;
import com.math.game.Entity.User;
import com.math.game.Entity.UserResult;
import com.math.game.Repository.UserRepository;
import com.math.game.Repository.UserResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserResultService {

    private final UserResultRepository userResultRepository;
    private final UserRepository userRepository;

    public void transformDToEntity(UserResultDTO userResultDTO, UserResult userResult){
        userResult.setFirstNumber(userResultDTO.getFirstNumber());
        userResult.setSecondNumber(userResultDTO.getSecondNumber());
        userResult.setOperation(userResultDTO.getOperation());
        userResult.setCorrectAnswer(userResultDTO.getCorrectAnswer());
        userResult.setUserFirstNumber(userResultDTO.getUserFirstNumber());
        userResult.setUserSecondNumber(userResultDTO.getUserSecondNumber());
        userResult.setUserAnswer(userResultDTO.getUserAnswer());
    }

    public void saveResult(UserResultDTO userResultDTO) {
        UserResult userResult = new UserResult();
        transformDToEntity(userResultDTO,userResult);

        User user = userRepository.findById(userResultDTO.getUserId()).orElse(null);
        userResult.setUser(user);

        userResultRepository.save(userResult);
    }

    public List<UserResult> getAllUserResults(){
        return userResultRepository.findAll();
    }

    public List<UserResultResponseDTO> getAllUserResultsAsDTO(){
        return userResultRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UserResultResponseDTO convertToDTO(UserResult userResult){
        UserResultResponseDTO dto = new UserResultResponseDTO();
        dto.setId(userResult.getId());
        dto.setFirstNumber(userResult.getFirstNumber());
        dto.setSecondNumber(userResult.getSecondNumber());
        dto.setOperation(userResult.getOperation());
        dto.setCorrectAnswer(userResult.getCorrectAnswer());
        dto.setUserFirstNumber(userResult.getUserFirstNumber());
        dto.setUserSecondNumber(userResult.getUserSecondNumber());
        dto.setUserAnswer(userResult.getUserAnswer());

        if(userResult.getUser() != null){
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(userResult.getUser().getEmail());
            userDTO.setPassword(userResult.getUser().getPassword());
            dto.setUser(userDTO);
        }

        return dto;
    }
}
