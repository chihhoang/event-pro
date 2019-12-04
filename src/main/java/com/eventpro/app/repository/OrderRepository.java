package com.eventpro.app.repository;
import com.eventpro.app.model.Asset;
import com.eventpro.app.model.OrderItem;
import com.eventpro.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByUser(User user);
}
