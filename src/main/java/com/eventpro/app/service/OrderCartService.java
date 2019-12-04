package com.eventpro.app.service;

import com.eventpro.app.model.OrderCart;
import com.eventpro.app.model.OrderCheckoutPojo;
import com.eventpro.app.model.User;

/**@author anvitha - store order details in DB after order checkout*/

public interface OrderCartService {

	OrderCart checkoutSubmit(OrderCheckoutPojo cartItems, User user);

}
