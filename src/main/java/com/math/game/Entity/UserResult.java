package com.math.game.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int firstNumber;
    @NotNull
    private int secondNumber;
    @NotBlank
    private String operation;
    @NotNull
    private int correctAnswer;
    @NotNull
    private int userFirstNumber;
    @NotNull
    private int userSecondNumber;
    @NotNull
    private int userAnswer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}