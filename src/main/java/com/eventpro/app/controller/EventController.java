package com.eventpro.app.controller;

import com.eventpro.app.model.Event;
import com.eventpro.app.security.JwtTokenProvider;
import com.eventpro.app.service.EventService;
import java.time.Instant;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/** @author choang on 10/23/19 */
/** @author manishayacham on 11/19/19 - implemented List API's */
@CrossOrigin("*")
@RestController
@RequestMapping("/events")
@Slf4j
@RequiredArgsConstructor
public class EventController {
  private final JwtTokenProvider tokenProvider;
  private final EventService eventService;

  @PostMapping
  public ResponseEntity<Event> createEvent(
      @RequestParam(value = "file") MultipartFile file,
      @RequestParam String description,
      @RequestParam int totalTickets,
      @RequestParam double ticketPrice,
      @RequestParam(required = false) Instant startTime,
      @RequestParam(required = false) Instant endTime,
      HttpServletRequest request) {
    String username = tokenProvider.getUserLogin(tokenProvider.resolveToken(request));

    return ResponseEntity.ok(
        eventService.createEvent(
            description, file, totalTickets, ticketPrice, startTime, endTime, username));
  }

  @GetMapping("/list")
  public ResponseEntity<List<Event>> listEvents() {
    return ResponseEntity.ok(eventService.listEvents());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Event> deleteEvent(@PathVariable long id, HttpServletRequest request) {
    String username = tokenProvider.getUserLogin(tokenProvider.resolveToken(request));

    // TODO Verify the actual user or admin before delete, pass username to service
    eventService.deleteEvent(id);

    return ResponseEntity.ok(Event.builder().id(id).build());
  }
}
