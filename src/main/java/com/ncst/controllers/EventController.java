package com.ncst.controllers;

import com.ncst.entity.Event;
import com.ncst.entity.Status;
import com.ncst.repositories.EventRepository;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

  @Autowired
  private EventRepository eventRepository;

  @GetMapping(value = "/home")
  public String home() {
    return "";
  }
  @GetMapping(value = "/send")
  public String execute() {
    Event event = new Event();
    event.setId(UUID.randomUUID());
    event.setStatus(Status.INIT);
    event.setCreationDate(OffsetDateTime.now());
    eventRepository.save(event);
    return "Sent";
  }
}
