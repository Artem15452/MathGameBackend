package com.math.game.Controller;

import com.math.game.DTO.GameResultDTO;
import com.math.game.Service.GameResultService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/gameResults")
public class GameResultRestController {

    private final GameResultService gameResultService;

    @PostMapping("/save")
    public ResponseEntity<String> saveGameResult(@Valid @RequestBody GameResultDTO gameResultDTO){
        gameResultService.saveGameResult(gameResultDTO);
        return ResponseEntity.ok("Game result saved");
    }
}
