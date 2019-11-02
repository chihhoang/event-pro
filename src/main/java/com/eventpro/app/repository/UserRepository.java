package com.eventpro.app.repository;

import com.eventpro.app.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findOneByEmailIgnoreCase(String email);

  Optional<User> findOneByUsername(String username);
}
