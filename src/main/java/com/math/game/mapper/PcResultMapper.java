package com.math.game.mapper;

import org.mapstruct.Mapper;
import com.math.game.Entity.PcResult;
import com.math.game.DTO.PcResultDTO;

@Mapper(componentModel = "spring")
public interface PcResultMapper {
    PcResult toPcResult(PcResultDTO pcResult);
}
