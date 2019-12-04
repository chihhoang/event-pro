package com.eventpro.app.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemPojo {
    private int quantity;
    private double price;
    private boolean purchased;
    private long eventId;

}
