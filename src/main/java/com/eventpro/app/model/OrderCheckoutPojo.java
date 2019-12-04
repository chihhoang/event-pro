package com.eventpro.app.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
/**@author anvitha*/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderCheckoutPojo {
    private List<OrderItemPojo> orderItemList;
    private double totalCost;
}
