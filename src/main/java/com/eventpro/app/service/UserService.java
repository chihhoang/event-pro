package com.eventpro.app.service;

import com.eventpro.app.model.Login;
import com.eventpro.app.model.User;
import com.eventpro.app.model.UserDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface UserService {
  List<User> getAll();

  User createUser(UserDTO user);

  User getUserById(long id);

  User getUserByUsername(String username);

  User whoami(HttpServletRequest request);

  User updateUser(Login login);
}
