package com.eventpro.app.service;

import com.eventpro.app.model.Event;
import java.time.Instant;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/** @author choang on 10/24/19 */
public interface EventService {
  Event createEvent(
	  String eventName,
      String description,
      MultipartFile multipartFile,
      int totalTickets,
      double ticketPrice,
      Instant startTime,
      Instant endTime,
      String username);

  Event getEvent(long id);

  Event deleteEvent(long id);

  Event deleteEvent(long id, String username);

  List<Event> listEvents();
  
  Event updateEvent(Event event);
}
