package com.eventpro.app.service;

import com.eventpro.app.model.OrderItem;

import java.util.List;

/** @author manishayacham on 11/19/19 */

public interface OrderService {
	
	OrderItem createOrder(
			 int quantity,
		     double price,
		     String username,
		     long id);

	/** @author anvitha - get orders history for a user */
	List<OrderItem> getAllOrdersForUser(String username);

}
