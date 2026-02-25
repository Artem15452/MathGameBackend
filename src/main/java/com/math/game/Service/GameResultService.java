package com.math.game.Service;

import com.math.game.DTO.GameResultDTO;
import com.math.game.DTO.PcResultDTO;
import com.math.game.DTO.UserResultDTO;
import com.math.game.Entity.User;
import com.math.game.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class GameResultService {

    private final UserResultService userResultService;
    private final PcResultService pcResultService;
    private final UserService userService;
    private final UserRepository userRepository;


    public void saveGameResult(GameResultDTO gameResultDTO) {

        User user = userRepository.findByEmail(gameResultDTO.getUserEmail());

        UserResultDTO userResultDTO = new UserResultDTO();
        userResultDTO.setFirstNumber(gameResultDTO.getFirstNumber());
        userResultDTO.setSecondNumber(gameResultDTO.getSecondNumber());
        userResultDTO.setOperation(gameResultDTO.getOperation());
        userResultDTO.setCorrectAnswer(gameResultDTO.getCorrectAnswer());
        userResultDTO.setUserFirstNumber(gameResultDTO.getUserFirstNumber());
        userResultDTO.setUserSecondNumber(gameResultDTO.getUserSecondNumber());
        userResultDTO.setUserAnswer(gameResultDTO.getUserAnswer());

        if (user != null) {
            userResultDTO.setUserId(user.getId());
        }

        userResultService.saveResult(userResultDTO);



        PcResultDTO pcResultDTO = new PcResultDTO();
        pcResultDTO.setFirstNumber(gameResultDTO.getFirstNumber());
        pcResultDTO.setSecondNumber(gameResultDTO.getSecondNumber());
        pcResultDTO.setOperation(gameResultDTO.getOperation());
        pcResultDTO.setResult(gameResultDTO.getCorrectAnswer());

        pcResultService.saveResult(pcResultDTO);
    }
}

