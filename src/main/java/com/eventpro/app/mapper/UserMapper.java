package com.eventpro.app.mapper;

import com.eventpro.app.model.User;
import com.eventpro.app.model.UserDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
  User toUser(UserDTO user);

  @InheritInverseConfiguration
  UserDTO toDtoUser(User user);
}
