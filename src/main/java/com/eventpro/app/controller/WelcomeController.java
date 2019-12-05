package com.eventpro.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class WelcomeController {
  @Value("${app.env}")
  private String env;

  @GetMapping("/env")
  public String env() {
    return env;
  }

  @GetMapping("/healthCheck")
  public String healthCheck() {
    return "Health Check is Okay";
  }
}
