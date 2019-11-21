package com.eventpro.app.service.impl;
import org.springframework.stereotype.Service;

import com.eventpro.app.model.Event;
import com.eventpro.app.model.OrderItem;
import com.eventpro.app.model.User;
import com.eventpro.app.repository.OrderRepository;
import com.eventpro.app.service.EventService;
import com.eventpro.app.service.OrderService;
import com.eventpro.app.service.UserService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** @author manishayacham on 11/19/19 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	 private final OrderRepository orderRepository;
	 private final UserService userService;
	 private final EventService eventService;
	
	 @Override
	  public OrderItem createOrder(
			  int quantity,
			     double price,
			     String username,
			     long id) {

	    User user = userService.getUserByUsername(username);
	    Event event= eventService.getEvent(id);

	    return orderRepository.save(
	        OrderItem.builder()
	        .quantity(quantity)
	        .price(price)
	        .purchased(false)
	           .user(user)
	           .event(event)
	            .build());
	  }

}
