package com.eventpro.app;

import com.eventpro.app.model.Role;
import com.eventpro.app.model.UserDTO;
import com.eventpro.app.repository.UserRepository;
import com.eventpro.app.service.UserService;
import com.google.common.collect.Sets;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class EventProApplication {
  @Value("${app.env}")
  private String env;

  @Resource private UserService userService;
  @Resource private UserRepository userRepository;

  public static void main(String[] args) {
    SpringApplication.run(EventProApplication.class, args);
  }

  @Bean
  public CommandLineRunner startup() {
    return args -> {
      log.info("========== Starting app... in {} env", env);

      if (!userRepository.findOneByUsername("admin").isPresent()) {
        UserDTO admin =
            UserDTO.builder()
                .username("admin")
                .password("admin")
                .email("chhoang102@gmail.com")
                .activated(true)
                .createdBy("admin")
                .firstName("Chi")
                .lastName("Hoang")
                .imageUrl("http://example.com")
                .roles(Sets.newHashSet(Role.ROLE_ADMIN, Role.ROLE_USER))
                .build();

        userService.createUser(admin);
      }

      if (!userRepository.findOneByUsername("user").isPresent()) {
        UserDTO user =
            UserDTO.builder()
                .username("user")
                .password("user")
                .email("user@gmail.com")
                .activated(true)
                .createdBy("admin")
                .firstName("Bruce")
                .lastName("Wayne")
                .imageUrl("http://example.com")
                .roles(Sets.newHashSet(Role.ROLE_USER))
                .build();

        userService.createUser(user);
      }
    };
  }
}
