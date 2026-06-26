package com.math.game.mapper;

import com.math.game.Entity.User;
import com.math.game.DTO.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO userDTO);

}
