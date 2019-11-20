package com.eventpro.app.controller;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eventpro.app.model.OrderItem;
import com.eventpro.app.security.JwtTokenProvider;
import com.eventpro.app.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** @author manishayacham on 11/19/19 */
@CrossOrigin("*")
@RestController
@RequestMapping("/Order")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
	
	private final JwtTokenProvider tokenProvider;
	private final OrderService orderService;

	  @PostMapping("/create")
	  public ResponseEntity<OrderItem> createOrder(
	      @RequestParam int quantity,
	      @RequestParam double price,
	      @RequestParam long id,
	      HttpServletRequest request) {
	    String username = tokenProvider.getUserLogin(tokenProvider.resolveToken(request));

	    return ResponseEntity.ok(
	        orderService.createOrder(
	        		quantity, price, username,id));
	  }


}
