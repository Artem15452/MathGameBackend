package com.math.game.Controller;
import com.math.game.DTO.UserResultResponseDTO;
import com.math.game.Service.UserResultService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/userResults")
@AllArgsConstructor
public class UserResultRestController {

    private final UserResultService userResultService;

    @GetMapping
    public ResponseEntity<List<UserResultResponseDTO>> getAllUserResults(){
        List<UserResultResponseDTO> userResults = userResultService.getAllUserResultsAsDTO();
        return ResponseEntity.ok(userResults);
    }
}
