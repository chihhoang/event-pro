package com.eventpro.app.repository;
import com.eventpro.app.model.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCartRepository extends JpaRepository<OrderCart, Long> {
	
}
