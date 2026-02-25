package com.math.game.Service;

import com.math.game.DTO.PcResultDTO;
import com.math.game.Entity.PcResult;
import com.math.game.Repository.PcResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PcResultService {

    private final PcResultRepository pcResultRepository;

    public void transformDToEntity(PcResultDTO pcResultDTO,PcResult pcResult){
        pcResult.setResult(pcResultDTO.getResult());
        pcResult.setOperation(pcResultDTO.getOperation());
        pcResult.setSecondNumber(pcResultDTO.getSecondNumber());
        pcResult.setFirstNumber(pcResultDTO.getFirstNumber());
    }

    public void saveResult(PcResultDTO pcResultDTO) {

        PcResult pcResult = new PcResult();
        transformDToEntity(pcResultDTO,pcResult);
        pcResultRepository.save(pcResult);

    }
}
