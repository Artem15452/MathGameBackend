package com.math.game.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PcResultDTO {

    private Long id;
    private int firstNumber;
    private int secondNumber;
    private String operation;
    private int result;

}
