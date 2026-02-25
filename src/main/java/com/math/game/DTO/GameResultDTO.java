package com.math.game.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class GameResultDTO {
        private String userEmail;
        private int firstNumber;
        private int secondNumber;
        private String operation;
        private int userFirstNumber;
        private int userSecondNumber;
        private int userAnswer;
        private int correctAnswer;
}
