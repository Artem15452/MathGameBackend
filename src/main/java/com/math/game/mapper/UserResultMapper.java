package com.math.game.mapper;

import org.mapstruct.Mapper;
import com.math.game.Entity.UserResult;
import com.math.game.Entity.User;
import com.math.game.DTO.UserResultDTO;
import com.math.game.DTO.UserResultResponseDTO;
import com.math.game.DTO.UserDTO;

@Mapper(componentModel = "spring")
public interface UserResultMapper {

    UserResult toEntity(UserResultDTO userResultDTO);

    UserResultResponseDTO toUserResultResponseDTO(UserResult userResult);

    UserDTO toUserDTO(User user);
}