package com.math.game.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResultResponseDTO {
    private Long id;
    private int firstNumber;
    private int secondNumber;
    private String operation;
    private int correctAnswer;
    private int userFirstNumber;
    private int userSecondNumber;
    private int userAnswer;
    private UserDTO user;
}
