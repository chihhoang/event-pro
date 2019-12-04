package com.eventpro.app.service.impl;

import com.eventpro.app.exception.SystemException;
import com.eventpro.app.model.*;
import com.eventpro.app.repository.OrderCartRepository;
import com.eventpro.app.repository.OrderRepository;
import com.eventpro.app.service.EventService;
import com.eventpro.app.service.OrderCartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderCartServiceImpl implements OrderCartService {

  private final OrderCartRepository orderCartRepository;
  private final OrderRepository orderRepository;
  private final EventService eventService;



  @Override
  public OrderCart checkoutSubmit(OrderCheckoutPojo orderCheckoutPojo, User user) {

    Set<OrderItem> orderItems = new HashSet<>();

//    logHelper("checkoutSubmit data passed : ", orderCheckoutPojo);

    orderCheckoutPojo.getOrderItemList().forEach( (order) -> {
      Event event = eventService.getEvent(order.getEventId());
      
      int currentAvailableTickets = event.getAvailableTickets();
      int purchasedTickets = order.getQuantity();
      if (currentAvailableTickets < purchasedTickets) {
    	  throw new SystemException("Tickets not available!!! " , HttpStatus.BAD_REQUEST);
      }
      currentAvailableTickets -= purchasedTickets;
      event.setAvailableTickets(currentAvailableTickets);
      
      // Update event with purchased tickets
      eventService.updateEvent(event);
      
      
      // Insert Data into OrderItem Table
      OrderItem orderItem = OrderItem.builder()
              .quantity(order.getQuantity())
              .price(order.getPrice())
              .purchased(true)
              .user(user)
              .event(event)
              .build();
      OrderItem orderSaved = orderRepository.save(orderItem);
      orderItems.add(orderSaved);
    });

    // Insert Data into OrderCart Table
    OrderCart cartData = OrderCart.builder()
            .orderItems(orderItems)
            .totalCost(orderCheckoutPojo.getTotalCost())
            .user(user)
            .build();

//    logHelper("checkoutSubmit : before save into cart table", cartData);


    return orderCartRepository.save(cartData) ;
  }

  private void logHelper(String message, Object data){
    ObjectMapper mapper = new ObjectMapper();
    try {
      System.out.println(message + " - " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data));
    } catch (JsonProcessingException e) {
      log.error("JsonProcessingException", e);
      e.printStackTrace();
    }
  }
}
