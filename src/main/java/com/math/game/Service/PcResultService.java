package com.math.game.Service;

import com.math.game.DTO.PcResultDTO;
import com.math.game.Entity.PcResult;
import com.math.game.Repository.PcResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.math.game.mapper.PcResultMapper;

@AllArgsConstructor
@Service
public class PcResultService {

    private final PcResultRepository pcResultRepository;
    private final PcResultMapper pcResultMapper;

    public void saveResult(PcResultDTO pcResultDTO) {

        PcResult pcResult = pcResultMapper.toPcResult(pcResultDTO);
        pcResultRepository.save(pcResult);

    }
}
