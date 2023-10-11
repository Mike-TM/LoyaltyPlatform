package it.unicam.loyaltyplatform.cliente;

import it.unicam.loyaltyplatform.dtos.SignUpDto;
import it.unicam.loyaltyplatform.dtos.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

        UserDto toUserDto(Cliente user);
        @Mapping(target = "password", ignore = true)
        Cliente signUpToUser(SignUpDto signUpDto);

}
