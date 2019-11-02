package com.eventpro.app.repository;

import com.eventpro.app.model.Event;
import com.eventpro.app.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
  List<Event> findByUser(User user);
}
