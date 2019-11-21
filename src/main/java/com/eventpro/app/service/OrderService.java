package com.eventpro.app.service;

import com.eventpro.app.model.OrderItem;

/** @author manishayacham on 11/19/19 */
public interface OrderService {
	
	OrderItem createOrder(
			 int quantity,
		     double price,
		     String username,
		     long id);

}
