package com.eventpro.app.controller;

import com.eventpro.app.model.*;
import com.eventpro.app.service.OrderCartService;
import com.eventpro.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
/**@author anvitha*/
@CrossOrigin("*")
@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderCartController {
	
	private final UserService userService;
	private final OrderCartService orderCartService;

	@PostMapping("/checkout")
	public ResponseEntity<OrderCart> checkout(@Valid @RequestBody OrderCheckoutPojo orderCart,
											  HttpServletRequest request) {
		User user = userService.whoami(request);

		return ResponseEntity.ok(
				orderCartService.checkoutSubmit(orderCart, user));
	}


}
