package com.math.game.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PcResult {

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
    private int result;   
}