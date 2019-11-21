package com.eventpro.app.service.impl;

import com.eventpro.app.model.Asset;
import com.eventpro.app.model.Event;
import com.eventpro.app.repository.EventRepository;
import com.eventpro.app.service.AmazonS3Service;
import com.eventpro.app.service.EventService;
import com.eventpro.app.util.Utils;
import java.time.Instant;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/** @author choang on 11/2/19 */
/** @author manishayacham on 11/19/19 - implemented List API's*/
@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
  private final EventRepository eventRepository;
  private final AmazonS3Service amazonS3Service;

  @Override
  public Event createEvent(
      String description,
      MultipartFile multipartFile,
      int totalTickets,
      double ticketPrice,
      Instant startTime,
      Instant endTime,
      String username) {

    Asset asset = amazonS3Service.uploadFile("event-" + Utils.getUUID(5), multipartFile, username);

    return eventRepository.save(
        Event.builder()
            .description(description)
            .imageUrl(asset.getS3Url())
            .totalTickets(totalTickets)
            .availableTickets(totalTickets)
            .ticketPrice(ticketPrice)
            .startTime(startTime)
            .endTime(endTime)
            .user(asset.getUser())
            .build());
  }

  @Override
  public Event getEvent(long id) {
    return eventRepository.findAllById(id);
  }

  @Override
  public Event deleteEvent(long id) {
    return null;
  }

  @Override
  public Event deleteEvent(long id, String username) {
    return null;
  }

  @Override
  public List<Event> listEvents() { 
    return eventRepository.findAll();
  }
}
